package com.history.tree.dto

import com.history.tree.model.EdgeType
import java.util.*

data class EdgeDto(
    val id: UUID,
    val treeViewId: UUID,
    val x1: Float,
    val y1: Float,
    val x2: Float,
    val y2: Float,
    val type: EdgeType
)
