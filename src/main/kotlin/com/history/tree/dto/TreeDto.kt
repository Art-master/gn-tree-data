package com.history.tree.dto

data class TreeDto(
    val id: Long?,
    val name: String,
    val description: String?,
    val mainTreeView: TreeViewDto?
)