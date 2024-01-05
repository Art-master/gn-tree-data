package com.history.tree.controllers

import com.history.tree.dto.PersonDto
import com.history.tree.services.PersonService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/persons"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
class PersonController(val personService: PersonService) {

    @GetMapping(path = ["/get_by_tree_view"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun getAllByTreViewId(@RequestParam id: Long): Flow<PersonDto> {
        return personService.getByTreeViewId(id)
    }

}