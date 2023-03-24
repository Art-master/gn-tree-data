package com.history.tree.services

import com.history.tree.dto.RelationshipDTO
import com.history.tree.mappers.RelationshipMapper
import com.history.tree.repositories.RelationshipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import java.util.*

@Service
class RelationshipService(val repository: RelationshipRepository, val mapper: RelationshipMapper) {
    suspend fun findById(id: UUID): RelationshipDTO? {
        val relationship = repository.findById(id)
        relationship ?: return null
        return mapper.relationshipToDto(relationship)
    }

    suspend fun getByTreeId(treeId: UUID): Flow<RelationshipDTO> {
        return repository.findAllByTreeId(treeId)
            .map { r -> mapper.relationshipToDto(r) }
    }

}