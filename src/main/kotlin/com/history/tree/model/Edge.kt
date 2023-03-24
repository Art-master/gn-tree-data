package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("edge")
data class Edge(
    @Id
    @Column("id")
    private val id: UUID,

    @Column("start_coordinate_x")
    private val startCoordinateX: Float,

    @Column("start_coordinate_y")
    private val startCoordinateY: Float,

    @Column("end_coordinate_x")
    private val endCoordinateX: Float,

    @Column("end_coordinate_y")
    private val endCoordinateY: Float,

    @Column("edge_type")
    private val edgeType: EdgeType,

    @Column("tree_id")
    var treeId: UUID
)