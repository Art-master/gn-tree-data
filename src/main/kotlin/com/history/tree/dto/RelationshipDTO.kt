package com.history.tree.dto

import com.history.tree.model.RelationshipType

data class RelationshipDTO(
        val id: Long,
        val marriage: MarriageDTO? = null,
        val relatedPersonId: Long,
        val relationshipType: RelationshipType,
        val treeId: Long
)