package com.history.tree.services

import com.history.tree.dto.MarriageDTO
import com.history.tree.dto.RelationshipDTO
import com.history.tree.mappers.MarriageMapper
import com.history.tree.mappers.RelationshipMapper
import com.history.tree.repositories.MarriageRepository
import com.history.tree.repositories.RelationshipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import java.util.*

@Service
class RelationshipService(
    val relationshipRepository: RelationshipRepository,
    val marriageRepository: MarriageRepository,
    val relationshipMapper: RelationshipMapper,
    val marriageMapper: MarriageMapper
) {
    suspend fun findById(id: UUID): RelationshipDTO? {
        val relationship = relationshipRepository.findById(id)
        relationship ?: return null
        val marriageDTO = getMarriageDTO(relationship.marriageId)
        return relationshipMapper.relationshipToDto(relationship, marriageDTO)
    }

    suspend fun getMarriageDTO(marriageId: UUID?): MarriageDTO? {
        val marriage = if (marriageId != null)
            marriageRepository.findById(marriageId) else null
        return if (marriage != null) marriageMapper.entityToDTO(marriage) else null
    }

    suspend fun getByTreeId(treeId: UUID): Flow<RelationshipDTO> {
        return relationshipRepository.findAllByTreeId(treeId)
            .map { r -> relationshipMapper.relationshipToDto(r, getMarriageDTO(r.marriageId)) }
    }

}