package com.history.tree.dto

import java.util.*

data class PersonViewDto(
    val id: UUID,
    val coordinateX: Float,
    val coordinateY: Float,
    val personId: UUID,
    val treeViewId: UUID,
)