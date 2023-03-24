package com.history.tree.dto

import com.history.tree.model.EdgeType
import java.util.*

data class EdgeDTO(
    val id: UUID,
    val startCoordinateX: Float,
    val startCoordinateY: Float,
    val endCoordinateX: Float,
    val endCoordinateY: Float,
    val edgeType: EdgeType,
    val treeId: UUID
)
