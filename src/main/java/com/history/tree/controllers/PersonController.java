package com.history.tree.controllers;

import com.history.tree.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @GetMapping()
    public Person getById() {
        return new Person();
    }
}
