package com.history.tree.controllers;

import com.history.tree.model.Tree;
import com.history.tree.services.TreeTestDataGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/debug")
public class TestController {

    private final TreeTestDataGenerator treeTestDataGenerator;

    @PostMapping("create_test_tree")
    public Mono<Tree> createTestTree() {
        return treeTestDataGenerator.generate();
    }
}
