package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("edge")
data class Edge(
    @Id
    @Column("id")
    val id: UUID,

    @Column("start_coordinate_x")
    val startCoordinateX: Float,

    @Column("start_coordinate_y")
    val startCoordinateY: Float,

    @Column("end_coordinate_x")
    val endCoordinateX: Float,

    @Column("end_coordinate_y")
    val endCoordinateY: Float,

    @Column("edge_type")
    val edgeType: EdgeType,

    @Column("tree_id")
    var treeId: UUID,
)