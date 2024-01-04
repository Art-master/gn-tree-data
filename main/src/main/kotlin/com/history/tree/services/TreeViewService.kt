package com.history.tree.services

import com.history.tree.dto.TreeViewDto
import com.history.tree.mappers.TreeViewMapper
import com.history.tree.model.TreeView
import com.history.tree.repositories.TreeViewRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.core.FluentR2dbcOperations
import org.springframework.data.r2dbc.core.usingAndAwait
import org.springframework.stereotype.Service
import java.util.*

@Service
class TreeViewService(
    val repository: TreeViewRepository,
    val mapper: TreeViewMapper,
    val op: FluentR2dbcOperations
) {
    suspend fun findById(id: UUID): TreeViewDto? {
        val entity = repository.findById(id)
        entity ?: return null
        return mapper.entityToDto(entity)
    }

    suspend fun create(treeView: TreeViewDto, treeId: Long): TreeViewDto {
        val entity: TreeView = mapper.dtoToEntity(treeView, treeId)
        val saved = op.insert(entity.javaClass).usingAndAwait(entity)
        return mapper.entityToDto(saved)
    }

    suspend fun delete(id: UUID) {
        return repository.deleteById(id)
    }

    suspend fun edit(tree: TreeViewDto): TreeViewDto {
        val entity: TreeView = mapper.dtoToEntity(tree)
        val saved = repository.save(entity)
        return mapper.entityToDto(saved)
    }

    fun findAllByTreeId(id: Long): Flow<TreeViewDto> {
        TODO("Not yet implemented")
    }
}