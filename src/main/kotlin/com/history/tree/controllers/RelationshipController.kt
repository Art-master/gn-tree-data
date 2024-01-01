package com.history.tree.controllers

import com.history.tree.dto.RelationshipDto
import com.history.tree.services.RelationshipService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/relationships")
class RelationshipController(val relationshipService: RelationshipService) {

    @GetMapping("/get_by_tree")
    suspend fun getAllByTreeId(@RequestParam treeId: UUID): Flow<RelationshipDto> {
        return relationshipService.getByTreeViewId(treeId)
    }
}