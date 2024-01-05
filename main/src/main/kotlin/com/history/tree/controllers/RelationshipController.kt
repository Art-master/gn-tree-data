package com.history.tree.controllers

import com.history.tree.dto.RelationshipDto
import com.history.tree.services.RelationshipService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/relationships")
class RelationshipController(val relationshipService: RelationshipService) {

    @GetMapping("/get_by_tree_view")
    suspend fun getAllByTreeId(@RequestParam id: Long): Flow<RelationshipDto> {
        return relationshipService.getByTreeViewId(id)
    }
}