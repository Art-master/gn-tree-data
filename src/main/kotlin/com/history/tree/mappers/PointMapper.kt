package com.history.tree.mappers

import com.history.tree.dto.PointDTO
import com.history.tree.model.Point
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PointMapper : EntityMapper<Point, PointDTO>