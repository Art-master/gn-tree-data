package com.history.tree.mappers

import com.history.tree.dto.EdgeDTO
import com.history.tree.model.Edge
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PointMapper : EntityMapper<Edge, EdgeDTO>