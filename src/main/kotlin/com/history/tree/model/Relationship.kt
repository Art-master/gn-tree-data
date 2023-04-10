package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("relationship")
data class Relationship(

    @Id
    @Column("id")
    val id: UUID,

    @Column("marriage_id")
    val marriageId: UUID? = null,

    @Column("person_id")
    val personId: UUID,

    @Column("related_person_id")
    val relatedPersonId: UUID,

    @Column("relationship_type")
    val relationshipType: RelationshipType,

    @Column("tree_id")
    var treeId: UUID,
)