package com.history.tree.dto

import java.util.UUID

data class PersonLinkedEdgesDTO(
    val id: UUID,
    val personViewId: UUID,
    val edgeId: UUID,
    val treeId: UUID
)
