package com.history.tree.mappers

import com.history.tree.dto.MarriageDto
import com.history.tree.model.Marriage
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface MarriageMapper : EntityMapper<Marriage, MarriageDto>