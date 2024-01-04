package com.history.tree.mappers

import com.history.tree.dto.PersonViewDto
import com.history.tree.model.PersonView
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonViewMapper : EntityMapper<PersonView, PersonViewDto>