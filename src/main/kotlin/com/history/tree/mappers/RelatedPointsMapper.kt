package com.history.tree.mappers

import com.history.tree.dto.RelatedPointsDTO
import com.history.tree.model.RelatedPoints
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface RelatedPointsMapper : EntityMapper<RelatedPoints, RelatedPointsDTO>