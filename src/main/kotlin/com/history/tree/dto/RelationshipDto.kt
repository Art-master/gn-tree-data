package com.history.tree.dto

import com.history.tree.model.RelationshipType
import java.util.*

data class RelationshipDto(
    val id: UUID,
    val marriage: MarriageDto? = null,
    val personId: UUID,
    val relatedPersonId: UUID,
    val relationshipType: RelationshipType,
    val treeViewId: UUID,
)