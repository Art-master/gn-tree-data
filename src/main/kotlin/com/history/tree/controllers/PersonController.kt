package com.history.tree.controllers

import com.history.tree.dto.PersonDTO
import com.history.tree.services.PersonService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(value = ["/persons"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
class PersonController(val personService: PersonService) {

    @GetMapping
    suspend fun getById(@RequestParam id: UUID): PersonDTO? {
        return personService.findById(id)
    }

    @GetMapping(path = ["/get_by_tree"], produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun getAllByTreeId(@RequestParam treeId: UUID): Flow<PersonDTO> {
        return personService.getByTreeId(treeId)
    }
}