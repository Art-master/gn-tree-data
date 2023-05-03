package com.history.tree.controllers

import com.history.tree.dto.PersonDTO
import com.history.tree.services.PersonService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import java.util.*

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

    @PostMapping
    suspend fun create(@RequestBody person: PersonDTO): PersonDTO {
        return personService.create(person)
    }

    @DeleteMapping
    suspend fun delete(@RequestParam id: UUID) {
        return personService.delete(id)
    }

    @PatchMapping
    suspend fun edit(@RequestBody person: PersonDTO): PersonDTO {
        return personService.edit(person)
    }

}