package com.history.tree.controllers

import com.history.tree.dto.TreeViewDto
import com.history.tree.services.TreeViewService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/tree_views")
class TreeViewController(val treeViewService: TreeViewService) {

    @GetMapping
    suspend fun getById(@RequestParam id: UUID): TreeViewDto? {
        return treeViewService.findById(id)
    }

    @GetMapping("get_by_tree_id")
    suspend fun getAllByTreeId(@RequestParam id: Long): Flow<TreeViewDto> {
        return treeViewService.findAllByTreeId(id)
    }

    @PatchMapping
    suspend fun edit(@RequestBody tree: TreeViewDto): TreeViewDto {
        return treeViewService.edit(tree)
    }

    @PostMapping
    suspend fun create(@RequestParam treeId: Long, @RequestBody tree: TreeViewDto): TreeViewDto {
        return treeViewService.create(tree, treeId)
    }

    @DeleteMapping
    suspend fun delete(@RequestParam id: UUID) {
        return treeViewService.delete(id)
    }
}