package com.history.tree.services

import com.history.tree.dto.PersonDTO
import com.history.tree.dto.TreeDTO
import com.history.tree.mappers.TreeMapper
import com.history.tree.model.Person
import com.history.tree.model.Tree
import com.history.tree.repositories.TreeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.data.r2dbc.core.FluentR2dbcOperations
import org.springframework.data.r2dbc.core.usingAndAwait
import org.springframework.stereotype.Service
import java.util.*

@Service
class TreeService(
    val repository: TreeRepository, val mapper: TreeMapper,
    val op: FluentR2dbcOperations
) {
    suspend fun findById(id: UUID): TreeDTO? {
        val entity = repository.findById(id)
        entity ?: return null
        return mapper.entityToDTO(entity)
    }

    suspend fun findAllByUserId(id: Long): Flow<TreeDTO> {
        return repository.findAll()
            .map { entity -> mapper.entityToDTO(entity) } //TODO Use user id
    }

    suspend fun findAll(): Flow<TreeDTO> {
        return repository.findAll()
            .map { entity -> mapper.entityToDTO(entity) }
    }

    suspend fun create(tree: TreeDTO): TreeDTO {
        val entity: Tree = mapper.dtoToEntity(tree)
        val saved = op.insert(entity.javaClass).usingAndAwait(entity)
        return mapper.entityToDTO(saved)
    }

    suspend fun delete(id: UUID) {
        return repository.deleteById(id)
    }

    suspend fun edit(tree: TreeDTO): TreeDTO {
        val entity: Tree = mapper.dtoToEntity(tree)
        val saved = repository.save(entity)
        return mapper.entityToDTO(saved)
    }
}