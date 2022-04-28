package com.history.tree.controllers;

import com.history.tree.dto.PersonDTO;
import com.history.tree.model.Person;
import com.history.tree.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("get_by_tree")
    public Flux<PersonDTO> getAllByTreeId(@RequestParam Long treeId) {
        return personService.getPersonsByTreeId(treeId);
    }
}
