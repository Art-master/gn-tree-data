package com.history.tree.mappers

import com.history.tree.dto.PersonDTO
import com.history.tree.model.Person
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface PersonMapper {

    fun personToDto(person: Person): PersonDTO

    companion object {
        val INSTANCE = Mappers.getMapper(PersonMapper::class.java)!!
    }
}