package com.history.tree.mappers

import com.history.tree.dto.MarriageDto
import com.history.tree.dto.RelationshipDto
import com.history.tree.model.Relationship
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface RelationshipMapper : EntityMapper<Relationship, RelationshipDto> {

    @Mapping(target = "marriage", source = "marriage")
    @Mapping(target = "id", source = "relationship.id")
    @Mapping(target = "personId", source = "relationship.personId")
    @Mapping(target = "relatedPersonId", source = "relationship.relatedPersonId")
    @Mapping(target = "relationshipType", source = "relationship.relationshipType")
    @Mapping(target = "treeViewId", source = "relationship.treeViewId")
    fun entityToDto(relationship: Relationship, marriage: MarriageDto?): RelationshipDto

    @InheritInverseConfiguration
    @Mapping(target = "marriageId", source = "dto.marriage.id")
    override fun dtoToEntity(dto: RelationshipDto): Relationship
}