package com.history.tree.services

import com.history.tree.dto.RelationshipDTO
import com.history.tree.mappers.RelationshipMapper
import com.history.tree.repositories.RelationshipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service

@Service
class RelationshipService(val repository: RelationshipRepository) {

    suspend fun findById(id: Long): RelationshipDTO {
        return RelationshipDTO(id = 1) //TODO Mock
    }

    suspend fun getByTreeId(treeId: Long): Flow<RelationshipDTO> {
        return repository.findRelationshipsByTree(treeId)
            .map { r -> RelationshipMapper.INSTANCE.relationshipToDto(r) }
    }

}