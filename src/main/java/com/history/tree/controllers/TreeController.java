package com.history.tree.controllers;

import com.history.tree.model.Tree;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tree")
public class TreeController {

    @GetMapping()
    public Tree getById() {
        return new Tree();
    }
}
