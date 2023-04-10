package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("person_view")
data class PersonView(
    @Id
    @Column("id")
    private val id: UUID,

    @Column("coordinate_x")
    private val coordinateX: Float,

    @Column("coordinate_y")
    private val coordinateY: Float,

    @Column("person_id")
    private val personId: UUID,

    @Column("tree_id")
    var treeId: UUID,
)