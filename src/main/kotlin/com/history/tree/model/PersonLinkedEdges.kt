package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("linked_edges")
data class PersonLinkedEdges(
    @Id
    @Column("id")
    private val id: UUID,

    @Column("person_view_id")
    private val personViewId: UUID,

    @Column("edge_id")
    private val edgeId: UUID,

    @Column("tree_id")
    var treeId: UUID,
)