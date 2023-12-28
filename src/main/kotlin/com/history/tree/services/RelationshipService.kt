package com.history.tree.services

import com.history.tree.dto.MarriageDto
import com.history.tree.dto.RelationshipDto
import com.history.tree.mappers.MarriageMapper
import com.history.tree.mappers.RelationshipMapper
import com.history.tree.model.Marriage
import com.history.tree.model.Relationship
import com.history.tree.repositories.MarriageRepository
import com.history.tree.repositories.RelationshipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.data.r2dbc.core.FluentR2dbcOperations
import org.springframework.data.r2dbc.core.usingAndAwait
import org.springframework.stereotype.Service
import java.util.*

@Service
class RelationshipService(
    val relationshipRepository: RelationshipRepository,
    val marriageRepository: MarriageRepository,
    val relationshipMapper: RelationshipMapper,
    val marriageMapper: MarriageMapper,
    val op: FluentR2dbcOperations
) {
    suspend fun findById(id: UUID): RelationshipDto? {
        val relationship = relationshipRepository.findById(id)
        relationship ?: return null
        val marriageDTO = getMarriageDTO(relationship.marriageId)
        return relationshipMapper.entityToDTO(relationship, marriageDTO)
    }

    suspend fun getMarriageDTO(marriageId: UUID?): MarriageDto? {
        val marriage = if (marriageId != null)
            marriageRepository.findById(marriageId) else null
        return if (marriage != null) marriageMapper.entityToDTO(marriage) else null
    }

    suspend fun getByTreeId(treeId: UUID): Flow<RelationshipDto> {
        return relationshipRepository.findAllByTreeId(treeId)
            .map { r -> relationshipMapper.entityToDTO(r, getMarriageDTO(r.marriageId)) }
    }

    suspend fun create(relationship: RelationshipDto): RelationshipDto {
        if (relationship.marriage != null) {
            val marriageEntity: Marriage = marriageMapper.dtoToEntity(relationship.marriage)
            op.insert(marriageEntity.javaClass).usingAndAwait(marriageEntity)
        }
        val relationshipEntity: Relationship = relationshipMapper.dtoToEntity(relationship)
        val saved = op.insert(relationshipEntity.javaClass).usingAndAwait(relationshipEntity)
        val marriageDTO = getMarriageDTO(saved.marriageId)
        return relationshipMapper.entityToDTO(saved, marriageDTO)
    }

    suspend fun delete(id: UUID) {
        return relationshipRepository.deleteById(id)
    }

    suspend fun edit(relationship: RelationshipDto): RelationshipDto {
        if (relationship.marriage != null) {
            val marriageEntity: Marriage = marriageMapper.dtoToEntity(relationship.marriage)
            marriageRepository.save(marriageEntity)
        }
        val relationshipEntity: Relationship = relationshipMapper.dtoToEntity(relationship)
        val savedRelationship = relationshipRepository.save(relationshipEntity)
        val marriageDTO = getMarriageDTO(savedRelationship.marriageId)
        return relationshipMapper.entityToDTO(savedRelationship, marriageDTO)
    }

}