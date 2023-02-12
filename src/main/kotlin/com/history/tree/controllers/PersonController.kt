package com.history.tree.controllers

import com.history.tree.dto.PersonDTO
import com.history.tree.services.PersonService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/persons"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
class PersonController(val personService: PersonService) {

    @GetMapping
    suspend fun getById(@RequestParam id: Long): PersonDTO {
        return personService.findById(id)
    }

    @GetMapping(value = ["get_by_tree"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun getAllByTreeId(@RequestParam treeId: Long): Flow<PersonDTO> {
        return personService.getPersonsByTreeId(treeId)
    }
}