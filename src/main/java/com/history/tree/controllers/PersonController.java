package com.history.tree.controllers;

import com.history.tree.dto.PersonDTO;
import com.history.tree.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public Mono<PersonDTO> getById(@RequestParam Long id) {
        return personService.findById(id);
    }

    @GetMapping(value = "get_by_tree", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PersonDTO> getAllByTreeId(@RequestParam Long treeId) {
        return personService.getPersonsByTreeId(treeId).take(4);
    }
}
