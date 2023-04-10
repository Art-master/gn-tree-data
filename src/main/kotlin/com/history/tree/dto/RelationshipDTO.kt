package com.history.tree.dto

import com.history.tree.model.RelationshipType
import java.util.*

data class RelationshipDTO(
        val id: UUID,
        val marriage: MarriageDTO? = null,
        val personId: UUID,
        val relatedPersonId: UUID,
        val relationshipType: RelationshipType,
        val treeId: UUID,
)