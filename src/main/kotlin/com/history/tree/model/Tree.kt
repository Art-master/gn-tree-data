package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("tree")
data class Tree(
        @Id
        @Column("id")
        val id: UUID,

        @Column("name")
        val name: String,

        @Column("description")
        val description: String,
)