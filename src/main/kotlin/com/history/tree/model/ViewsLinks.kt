package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("views_links")
data class ViewsLinks(
    @Id
    @Column("id")
    val id: UUID,

    @Column("view_id")
    val viewId: UUID,

    @Column("linked_view_id")
    val linkedViewId: UUID,

    @Column("tree_id")
    val treeId: UUID,
)