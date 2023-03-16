package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("related_points")
data class RelatedPoints(
    @Id
    @Column("id")
    private val id: Long = 0,

    @Column("person_view_id")
    private val personViewId: Long,

    @Column("point_id")
    private val pointId: Long,

    @Column("tree_id")
    var treeId: Long,
)