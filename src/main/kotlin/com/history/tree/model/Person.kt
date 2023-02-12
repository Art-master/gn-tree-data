package com.history.tree.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("person")
data class Person (
    @Id
    @Column("id")
    val id: Long = 0,

    @Column("first_name")
    var firstName: String?,

    @Column("last_name")
    var lastName: String?,

    @Column("patronymic")
    var patronymic: String?,

    @Column("tree_id")
    var treeId: Long,

    @Column("gender")
    var gender: Char,

    @Column("birth_date")
    var birthDate: LocalDate?,

    @Column("date_of_death")
    var dateOfDeath: LocalDate? = null,
)