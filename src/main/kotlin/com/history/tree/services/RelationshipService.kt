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
) : CommonTreeService<Relationship, RelationshipDto>(relationshipRepository, relationshipMapper) {
    override suspend fun findById(id: UUID): RelationshipDto? {
        val relationship = relationshipRepository.findById(id)
        relationship ?: return null
        val marriageDTO = getMarriageDto(relationship.marriageId)
        return relationshipMapper.entityToDto(relationship, marriageDTO)
    }

    suspend fun getMarriageDto(marriageId: UUID?): MarriageDto? {
        val marriage = if (marriageId != null)
            marriageRepository.findById(marriageId) else null
        return if (marriage != null) marriageMapper.entityToDto(marriage) else null
    }

    override suspend fun getByTreeViewId(treeId: UUID): Flow<RelationshipDto> {
        return relationshipRepository.findAllByTreeViewId(treeId)
            .map { r -> relationshipMapper.entityToDto(r, getMarriageDto(r.marriageId)) }
    }

    suspend fun create(relationship: RelationshipDto): RelationshipDto {
        if (relationship.marriage != null) {
            val marriageEntity: Marriage = marriageMapper.dtoToEntity(relationship.marriage)
            op.insert(marriageEntity.javaClass).usingAndAwait(marriageEntity)
        }
        val relationshipEntity: Relationship = relationshipMapper.dtoToEntity(relationship)
        val saved = op.insert(relationshipEntity.javaClass).usingAndAwait(relationshipEntity)
        val marriageDTO = getMarriageDto(saved.marriageId)
        return relationshipMapper.entityToDto(saved, marriageDTO)
    }

    override suspend fun delete(id: UUID) {
        return relationshipRepository.deleteById(id)
    }

    override suspend fun edit(entityDto: RelationshipDto): RelationshipDto {
        if (entityDto.marriage != null) {
            val marriageEntity: Marriage = marriageMapper.dtoToEntity(entityDto.marriage)
            marriageRepository.save(marriageEntity)
        }
        val relationshipEntity: Relationship = relationshipMapper.dtoToEntity(entityDto)
        val savedRelationship = relationshipRepository.save(relationshipEntity)
        val marriageDTO = getMarriageDto(savedRelationship.marriageId)
        return relationshipMapper.entityToDto(savedRelationship, marriageDTO)
    }

}