package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("marriage")
data class Marriage(
        @Id
        @Column("id")
        private val id: UUID,

        @Column("date_start")
        private val dateStart: String?,

        @Column("date_end")
        private val dateEnd: String?,

        @Column("tree_id")
        var treeId: UUID,
)