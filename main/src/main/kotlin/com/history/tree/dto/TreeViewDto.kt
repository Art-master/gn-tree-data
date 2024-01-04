package com.history.tree.dto

data class TreeViewDto(
    val id: Long?,
    val treeId: Long?,
    val color: Int,
    val name: String,
    val description: String?,
)