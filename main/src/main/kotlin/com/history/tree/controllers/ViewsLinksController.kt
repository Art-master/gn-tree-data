package com.history.tree.controllers

import com.history.tree.dto.ViewsLinkDto
import com.history.tree.services.ViewsLinksService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/views"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
class ViewsLinksController(val service: ViewsLinksService) {

    @GetMapping(path = ["/get_by_tree_view"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun getAllByTreeId(@RequestParam id: Long): Flow<ViewsLinkDto> {
        return service.getByTreeViewId(id)
    }
}