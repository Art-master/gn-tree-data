package com.history.tree.controllers

import com.history.tree.dto.TreeViewDto
import com.history.tree.services.TreeViewService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/tree_views")
class TreeViewController(val treeViewService: TreeViewService) {

    @GetMapping
    suspend fun getById(@RequestParam id: UUID): TreeViewDto? {
        return treeViewService.findById(id)
    }

    @GetMapping("get_main_by_tree_id")
    suspend fun findMainViewByTreeId(@RequestParam("tree_id") treeId: Long): TreeViewDto {
        return treeViewService.findMainViewByTreeId(treeId)
    }

    @PatchMapping
    suspend fun edit(@RequestBody tree: TreeViewDto): TreeViewDto {
        return treeViewService.edit(tree)
    }

    @PostMapping
    suspend fun create(@RequestParam("tree_id") treeId: Long, @RequestBody tree: TreeViewDto): TreeViewDto {
        return treeViewService.create(tree, treeId)
    }

    @DeleteMapping
    suspend fun delete(@RequestParam id: UUID) {
        return treeViewService.delete(id)
    }
}