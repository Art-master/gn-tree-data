package com.history.tree.dto

import java.util.UUID

data class PersonLinkedEdgesDto(
    val id: UUID,
    val personViewId: UUID,
    val edgeId: UUID,
    val treeId: UUID,
)
