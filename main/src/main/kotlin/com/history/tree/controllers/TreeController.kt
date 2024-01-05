package com.history.tree.controllers

import com.history.tree.dto.FullTreeDataDto
import com.history.tree.dto.TreeDto
import com.history.tree.extension.Auth
import com.history.tree.services.TreeService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/trees")
class TreeController(val treeService: TreeService) {

    @GetMapping
    suspend fun getById(@RequestParam id: Long): TreeDto? {
        return treeService.findById(id)
    }

    @GetMapping("get_user_trees")
    suspend fun getAllByUserId(): Flow<TreeDto> {
        val userId = Auth.getUserId()
        return treeService.findAllByUserId(userId)
    }

    @PatchMapping
    suspend fun edit(@RequestBody tree: TreeDto): TreeDto {
        return treeService.edit(tree)
    }

    @PostMapping
    suspend fun create(@RequestBody tree: TreeDto): TreeDto {
        return treeService.create(tree)
    }

    @PostMapping("save_all")
    suspend fun saveAll(
        @RequestParam(name = "tree_view_id") treeViewId: Long,
        @RequestBody treeData: FullTreeDataDto
    ) {
        treeService.saveAll(treeViewId, treeData)
    }

    @DeleteMapping
    suspend fun delete(@RequestParam id: Long) {
        return treeService.delete(id)
    }
}