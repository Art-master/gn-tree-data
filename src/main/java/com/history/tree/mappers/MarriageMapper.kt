package com.history.tree.mappers

import com.history.tree.dto.MarriageDTO
import com.history.tree.model.Marriage
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface MarriageMapper {
    fun relationshipToDto(marriage: Marriage): MarriageDTO

    companion object {
        val INSTANCE = Mappers.getMapper(MarriageMapper::class.java)!!
    }
}