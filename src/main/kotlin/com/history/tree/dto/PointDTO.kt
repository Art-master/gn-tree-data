package com.history.tree.dto

import com.history.tree.model.PointsConnectionType

data class PointDTO(
    val id: Long,
    val coordinate_x: Float,
    val coordinate_y: Float,
    val nextPointId: Long?,
    val connectionType: PointsConnectionType,
    val marriageRelationshipId: Int?,
    val treeId: Long
)
