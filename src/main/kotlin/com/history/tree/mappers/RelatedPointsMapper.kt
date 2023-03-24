package com.history.tree.mappers

import com.history.tree.dto.PersonLinkedEdgesDTO
import com.history.tree.model.PersonLinkedEdges
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface RelatedPointsMapper : EntityMapper<PersonLinkedEdges, PersonLinkedEdgesDTO>