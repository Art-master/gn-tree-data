package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("relationship")
data class Relationship(

    @Id
    @Column("id")
    private val id: UUID,

    @Column("marriage_id")
    private val marriageId: UUID? = null,

    @Column("person_id")
    private val personId: UUID,

    @Column("related_person_id")
    private val relatedPersonId: UUID,

    @Column("relationship_type")
    private val relationshipType: RelationshipType,

    @Column("tree_id")
    var treeId: UUID
)