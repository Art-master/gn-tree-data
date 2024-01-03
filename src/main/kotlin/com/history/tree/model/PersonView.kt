package com.history.tree.model

import com.history.tree.schemas.CommonSchema
import com.history.tree.schemas.PersonViewSchema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("person_view")
data class PersonView(
    @Id
    @Column(CommonSchema.id)
    val id: UUID,

    @Column(PersonViewSchema.tree_view_id)
    var treeViewId: UUID,

    @Column(PersonViewSchema.x)
    val x: Float,

    @Column(PersonViewSchema.y)
    val y: Float,

    @Column(PersonViewSchema.person_id)
    val personId: UUID
)