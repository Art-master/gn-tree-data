package com.history.tree.controllers

import com.history.tree.dto.EdgeDto
import com.history.tree.services.EdgeService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/edges"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
class EdgeController(val edgeService: EdgeService) {

    @GetMapping(path = ["/get_by_tree_view"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun getAllByTreViewId(@RequestParam id: UUID): Flow<EdgeDto> {
        return edgeService.getByTreeId(id)
    }
}