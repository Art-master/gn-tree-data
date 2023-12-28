package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("person_view")
data class PersonView(
    @Id
    @Column("id")
    val id: UUID,

    @Column("tree_id")
    var treeId: UUID,

    @Column("x")
    val x: Float,

    @Column("y")
    val y: Float,

    @Column("person_id")
    val personId: UUID
)