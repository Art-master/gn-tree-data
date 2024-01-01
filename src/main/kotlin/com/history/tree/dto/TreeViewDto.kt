package com.history.tree.dto

import java.util.*

data class TreeViewDto(
    val id: UUID,
    val treeId: UUID,
    val color: Int,
    val name: String,
    val description: String,
)