package com.history.tree.services

import com.history.tree.dto.ViewsLinkDto
import com.history.tree.mappers.ViewsLinksMapper
import com.history.tree.model.ViewsLinks
import com.history.tree.repositories.ViewsLinksRepository
import org.springframework.stereotype.Service

@Service
class ViewsLinksService(val repository: ViewsLinksRepository, val mapper: ViewsLinksMapper) :
    CommonTreeService<ViewsLinks, ViewsLinkDto>(repository, mapper)