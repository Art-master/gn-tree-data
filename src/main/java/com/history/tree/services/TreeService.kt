package com.history.tree.services

import com.history.tree.dto.TreeDTO
import com.history.tree.mappers.TreeMapper
import com.history.tree.model.Tree
import com.history.tree.repositories.TreeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service

@Service
class TreeService(val repository: TreeRepository) {

    suspend fun findById(id: Long): TreeDTO {
        val tree = repository.findById(id)
        return TreeMapper.INSTANCE.entityToDTO(tree!!)
    }

    suspend fun findAllByUserId(id: Long): Flow<TreeDTO> {
        return repository.findAll()
            .map { entity -> TreeMapper.INSTANCE.entityToDTO(entity) }
    }

    suspend fun findAll(): Flow<TreeDTO> {
        return repository.findAll()
            .map { entity -> TreeMapper.INSTANCE.entityToDTO(entity) }
    }

    suspend fun create(tree: TreeDTO): TreeDTO {
        val entity: Tree = TreeMapper.INSTANCE.dtoToEntity(tree)
        val saved = repository.save(entity)
        return TreeMapper.INSTANCE.entityToDTO(saved)
    }

    suspend fun delete(id: Long) {
        return repository.deleteById(id)
    }
}