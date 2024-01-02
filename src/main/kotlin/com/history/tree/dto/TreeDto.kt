package com.history.tree.dto

import com.history.tree.model.TreeView
import java.util.*

data class TreeDto(
    val id: UUID,
    val name: String,
    val description: String,
    val mainTreeView: TreeView
)