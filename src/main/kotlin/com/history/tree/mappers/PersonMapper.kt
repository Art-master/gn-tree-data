package com.history.tree.mappers

import com.history.tree.dto.PersonDTO
import com.history.tree.model.Person
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonMapper : EntityMapper<Person, PersonDTO>