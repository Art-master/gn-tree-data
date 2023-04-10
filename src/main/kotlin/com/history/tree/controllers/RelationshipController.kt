package com.history.tree.controllers

import com.history.tree.dto.RelationshipDTO
import com.history.tree.services.RelationshipService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/relationships")
class RelationshipController(val relationshipService: RelationshipService) {

    @GetMapping
    suspend fun getById(@RequestParam id: UUID): RelationshipDTO? {
        return relationshipService.findById(id)
    }

    @GetMapping("/get_by_tree")
    suspend fun getAllByTreeId(@RequestParam treeId: UUID): Flow<RelationshipDTO> {
        return relationshipService.getByTreeId(treeId)
    }
}