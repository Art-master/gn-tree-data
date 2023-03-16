package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("person_view")
data class PersonView(
    @Id
    @Column("id")
    private val id: Long = 0,

    @Column("coordinate_x")
    private val coordinate_x: Float,

    @Column("coordinate_y")
    private val coordinate_y: Float,

    @Column("person_id")
    private val personId: Long,

    @Column("tree_id")
    var treeId: Long
)