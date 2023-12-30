package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.UUID

@Table("marriage")
data class Marriage(
    @Id
    @Column("id")
    val id: UUID,

    @Column("date_start")
    val dateStart: LocalDate?,

    @Column("date_end")
    val dateEnd: LocalDate?,

    @Column("tree_view_id")
    var treeViewId: UUID,
)