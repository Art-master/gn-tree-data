package com.history.tree.mappers

import com.history.tree.dto.EdgeDto
import com.history.tree.model.Edge
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface EdgeMapper : EntityMapper<Edge, EdgeDto>