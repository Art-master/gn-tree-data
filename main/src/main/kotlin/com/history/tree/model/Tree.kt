package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("tree")
data class Tree(
    @Id
    @Column("id")
    val id: Long?,

    @Column("name")
    val name: String,

    @Column("description")
    val description: String?,

    @Column("user_id")
    val userId: Long,
)