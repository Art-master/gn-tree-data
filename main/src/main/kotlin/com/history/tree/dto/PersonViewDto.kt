package com.history.tree.dto

import java.util.*

data class PersonViewDto(
    val id: UUID,
    val x: Float,
    val y: Float,
    val personId: UUID,
    val treeViewId: UUID,
)