package com.history.tree.controllers

import com.history.tree.dto.TreeDTO
import com.history.tree.services.TreeService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/trees")
class TreeController(val treeService: TreeService) {

    @GetMapping
    suspend fun getById(@RequestParam id: UUID): TreeDTO {
        return treeService.findById(id)
    }

    @GetMapping("get_by_user")
    suspend fun getAllByUserId(@RequestParam id: Long): Flow<TreeDTO> {
        return treeService.findAllByUserId(id)
    }

    @PatchMapping
    suspend fun edit(@RequestBody tree: TreeDTO): TreeDTO {
        return treeService.edit(tree)
    }

    @PostMapping
    suspend fun create(@RequestBody tree: TreeDTO): TreeDTO {
        return treeService.create(tree)
    }

    @DeleteMapping
    suspend fun delete(@RequestParam id: UUID) {
        return treeService.delete(id)
    }
}