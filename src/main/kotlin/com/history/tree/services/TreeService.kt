package com.history.tree.services

import com.history.tree.dto.FullTreeDataDto
import com.history.tree.dto.TreeDto
import com.history.tree.mappers.TreeMapper
import com.history.tree.model.Tree
import com.history.tree.repositories.TreeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import org.springframework.data.r2dbc.core.FluentR2dbcOperations
import org.springframework.data.r2dbc.core.usingAndAwait
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TreeService(
    val repository: TreeRepository,
    val mapper: TreeMapper,
    val treeViewService: TreeViewService,
    val edgeService: EdgeService,
    val personViewService: PersonViewService,
    val personService: PersonService,
    val relationshipService: RelationshipService,
    val viewsLinksService: ViewsLinksService,
    val op: FluentR2dbcOperations
) {

    val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    suspend fun findById(id: UUID): TreeDto? {
        val entity = repository.findById(id)
        entity ?: return null
        return mapper.entityToDto(entity)
    }

    suspend fun findAllByUserId(id: Long): Flow<TreeDto> {
        return repository.findAll()
            .map { entity -> mapper.entityToDto(entity) } //TODO Use user id
    }

    @Transactional
    suspend fun create(tree: TreeDto): TreeDto {
        val entity: Tree = mapper.dtoToEntity(tree)
        val saved = op.insert(entity.javaClass).usingAndAwait(entity)

        treeViewService.create(tree.mainTreeView)
        return mapper.entityToDto(saved)
    }

    suspend fun delete(id: UUID) {
        return repository.deleteById(id)
    }

    suspend fun edit(tree: TreeDto): TreeDto {
        val entity: Tree = mapper.dtoToEntity(tree)
        val saved = repository.save(entity)
        return mapper.entityToDto(saved)
    }

    @Transactional
    fun saveAll(treeViewId: UUID, treeData: FullTreeDataDto) {
        coroutineScope.launch {
            val deleteAllEntities = coroutineScope.launch {
                //personService.deleteByTreeViewId(treeViewId) //TODO delete ?
                personViewService.deleteByTreeViewId(treeViewId)
                edgeService.deleteByTreeViewId(treeViewId)
                relationshipService.deleteByTreeViewId(treeViewId)
                viewsLinksService.deleteByTreeViewId(treeViewId)
            }
            deleteAllEntities.wait()

            personService.saveAll(treeData.persons)
            personViewService.saveAll(treeData.personViews)
            edgeService.saveAll(treeData.edges)
            relationshipService.saveAll(treeData.relationships)
            viewsLinksService.saveAll(treeData.viewsLinks)
        }

    }
}