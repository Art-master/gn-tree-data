package com.history.tree.controllers

import com.history.tree.model.Tree
import com.history.tree.services.TreeTestDataGenerator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/debug")
class TestController(val treeTestDataGenerator: TreeTestDataGenerator) {

    @PostMapping("create_test_tree")
    suspend fun createTestTree(): Tree {
        return treeTestDataGenerator.generate()
    }
}