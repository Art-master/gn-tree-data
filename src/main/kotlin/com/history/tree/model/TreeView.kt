package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("tree_view")
data class TreeView(
    @Id
    @Column("id")
    val id: Long,

    @Column("tree_id")
    val treeId: Long,

    @Column("name")
    val name: String,

    @Column("description")
    val description: String,

    @Column("color")
    val color: Int
)