package com.history.tree.controllers

import com.history.tree.dto.PersonViewDto
import com.history.tree.services.PersonViewService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/person_views"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
class PersonViewController(val personViewService: PersonViewService) {

    @GetMapping(path = ["/get_by_tree_view"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun getAllByTreViewId(@RequestParam id: UUID): Flow<PersonViewDto> {
        return personViewService.getByTreeViewId(id)
    }
}