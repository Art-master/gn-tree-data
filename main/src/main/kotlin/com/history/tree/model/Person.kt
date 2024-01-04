package com.history.tree.model

import com.history.tree.schemas.CommonSchema
import com.history.tree.schemas.PersonSchema
import com.history.tree.schemas.PersonSchema.PERSON
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.*

@Table(PERSON)
data class Person(
    @Id
    @Column(CommonSchema.id)
    val id: UUID,

    @Column(PersonSchema.first_name)
    var firstName: String?,

    @Column(PersonSchema.last_name)
    var lastName: String?,

    @Column(PersonSchema.patronymic)
    var patronymic: String?,

    @Column(PersonSchema.tree_view_id)
    var treeViewId: UUID,

    @Column(PersonSchema.gender)
    var gender: Char,

    @Column(PersonSchema.birth_date)
    var birthDate: LocalDate?,

    @Column(PersonSchema.date_of_death)
    var dateOfDeath: LocalDate?
)