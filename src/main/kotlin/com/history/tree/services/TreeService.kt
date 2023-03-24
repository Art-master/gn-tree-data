package com.history.tree.services

import com.history.tree.dto.TreeDTO
import com.history.tree.exceptions.NoSuchObjectException
import com.history.tree.exceptions.ValidationException
import com.history.tree.mappers.TreeMapper
import com.history.tree.model.Tree
import com.history.tree.repositories.TreeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import java.util.*

@Service
class TreeService(val repository: TreeRepository, val mapper: TreeMapper) {
    suspend fun findById(id: UUID): TreeDTO {
        val tree = repository.findById(id)
        return mapper.entityToDTO(tree!!)
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
        val saved = repository.save(entity)
        return mapper.entityToDTO(saved)
    }

    suspend fun delete(id: UUID) {
        return repository.deleteById(id)
    }

    suspend fun edit(tree: TreeDTO): TreeDTO {
        val entity: Tree = mapper.dtoToEntity(tree)
        val foundTree = repository.findById(tree.id)
        foundTree ?: throw NoSuchObjectException("Tree with id = ${tree.id} is not found")

        val saved = repository.save(entity)
        return mapper.entityToDTO(saved)
    }
}