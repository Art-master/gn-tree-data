package com.history.tree.services

import com.history.tree.dto.FullTreeDataDto
import com.history.tree.dto.TreeDto
import com.history.tree.extension.Auth
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
import org.springframework.r2dbc.connection.ConnectionFactoryUtils
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

    suspend fun findById(id: Long): TreeDto? {
        val entity = repository.findById(id)
        entity ?: return null
        return mapper.entityToDto(entity)
    }

    suspend fun findAllByUserId(id: Long): Flow<TreeDto> {
        val userId = Auth.getUserId()
        return repository.findByUserId(userId)
            .map { entity -> mapper.entityToDto(entity) }
    }

    @Transactional
    suspend fun create(tree: TreeDto): TreeDto {
        val userId = Auth.getUserId()
        val entity: Tree = mapper.dtoToEntity(tree, userId)
        val saved = op.insert(entity.javaClass).usingAndAwait(entity)

        return mapper.entityToDto(saved)
    }

    suspend fun delete(id: Long) {
        treeViewService.deleteAllByTreeId(id)
        return repository.deleteById(id)
    }

    suspend fun edit(tree: TreeDto): TreeDto {
        val userId = Auth.getUserId()
        val entity: Tree = mapper.dtoToEntity(tree, userId)
        val saved = repository.save(entity)
        return mapper.entityToDto(saved)
    }

    @Transactional
    fun saveAll(treeViewId: Long, treeData: FullTreeDataDto) {
        coroutineScope.launch {
            val deleteAllEntities = coroutineScope.launch {
                personService.deleteByTreeViewIdIfViewsNotFound(treeViewId)
                personViewService.deleteByTreeViewId(treeViewId)
                edgeService.deleteByTreeViewId(treeViewId)
                relationshipService.deleteByTreeViewId(treeViewId)
                viewsLinksService.deleteByTreeViewId(treeViewId)
            }
            deleteAllEntities.wait()

            //TODO checks before save data
            personService.saveAll(treeData.persons)
            personViewService.saveAll(treeData.personViews)
            edgeService.saveAll(treeData.edges)
            relationshipService.saveAll(treeData.relationships)
            viewsLinksService.saveAll(treeData.viewsLinks)
        }

    }
}