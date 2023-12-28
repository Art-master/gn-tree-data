package com.history.tree.controllers

import com.history.tree.dto.RelationshipDto
import com.history.tree.services.RelationshipService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/relationships")
class RelationshipController(val relationshipService: RelationshipService) {

    @GetMapping
    suspend fun getById(@RequestParam id: UUID): RelationshipDto? {
        return relationshipService.findById(id)
    }

    @GetMapping("/get_by_tree")
    suspend fun getAllByTreeId(@RequestParam treeId: UUID): Flow<RelationshipDto> {
        return relationshipService.getByTreeId(treeId)
    }

    @PostMapping
    suspend fun create(@RequestBody relationship: RelationshipDto): RelationshipDto {
        return relationshipService.create(relationship)
    }

    @DeleteMapping
    suspend fun delete(@RequestParam id: UUID) {
        return relationshipService.delete(id)
    }

    @PatchMapping
    suspend fun edit(@RequestBody relationship: RelationshipDto): RelationshipDto {
        return relationshipService.edit(relationship)
    }
}