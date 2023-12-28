package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("edge")
data class Edge(
    @Id
    @Column("id")
    val id: UUID,

    @Column("x1")
    val x1: Float,

    @Column("y1")
    val y1: Float,

    @Column("x2")
    val x2: Float,

    @Column("y2")
    val y2: Float,

    @Column("type")
    val type: EdgeType,

    @Column("tree_id")
    var treeId: UUID,
)