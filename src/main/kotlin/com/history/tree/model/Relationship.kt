package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("relationship")
class Relationship(

    @Id
    @Column("id")
    private val id: Long = 0,

    @Column("marriage_id")
    private val marriageId: Long? = null,

    @Column("person_id")
    private val personId: Long,

    @Column("related_person_id")
    private val relatedPersonId: Long,

    @Column("relationship_type")
    private val relationshipType: Int
)