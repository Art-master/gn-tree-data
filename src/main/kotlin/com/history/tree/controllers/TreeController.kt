package com.history.tree.controllers

import com.history.tree.dto.FullTreeDataDto
import com.history.tree.dto.TreeDto
import com.history.tree.services.TreeService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/trees")
class TreeController(val treeService: TreeService) {

    @GetMapping
    suspend fun getById(@RequestParam id: UUID): TreeDto? {
        return treeService.findById(id)
    }

    @GetMapping("get_by_user")
    suspend fun getAllByUserId(@RequestParam id: Long): Flow<TreeDto> {
        return treeService.findAllByUserId(id)
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
    suspend fun saveAll(@RequestBody treeData: FullTreeDataDto) {
        treeService.saveAll(treeData)
    }

    @DeleteMapping
    suspend fun delete(@RequestParam id: UUID) {
        return treeService.delete(id)
    }
}