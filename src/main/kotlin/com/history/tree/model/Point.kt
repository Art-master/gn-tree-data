package com.history.tree.model

import org.mapstruct.EnumMapping
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("point")
data class Point(
    @Id
    @Column("id")
    private val id: Long = 0,

    @Column("coordinate_x")
    private val coordinate_x: Float,

    @Column("coordinate_y")
    private val coordinate_y: Float,

    @Column("next_point_id")
    private val nextPointId: Long?,

    @Column("connection_type")
    private val connectionType: PointsConnectionType,

    @Column("marriage_relationship_id")
    private val marriageRelationshipId: Int?,

    @Column("tree_id")
    var treeId: Long
)