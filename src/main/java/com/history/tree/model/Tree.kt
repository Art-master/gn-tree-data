package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("tree")
data class Tree(
        @Id
        @Column("id")
        val id: Long = 0,

        @Column("name")
        val name: String,
)