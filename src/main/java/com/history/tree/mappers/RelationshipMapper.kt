package com.history.tree.mappers

import com.history.tree.dto.MarriageDTO
import com.history.tree.dto.RelationshipDTO
import com.history.tree.model.Relationship
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper
interface RelationshipMapper {

    @Mapping(target = "marriage", source = "marriage")
    fun relationshipToDto(relationship: Relationship, marriage: MarriageDTO): RelationshipDTO

    fun relationshipToDto(relationship: Relationship): RelationshipDTO

    companion object {
        val INSTANCE = Mappers.getMapper(RelationshipMapper::class.java)!!
    }
}