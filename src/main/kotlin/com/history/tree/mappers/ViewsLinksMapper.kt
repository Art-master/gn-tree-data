package com.history.tree.mappers

import com.history.tree.dto.ViewsLinkDto
import com.history.tree.model.ViewsLinks
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ViewsLinksMapper : EntityMapper<ViewsLinks, ViewsLinkDto>