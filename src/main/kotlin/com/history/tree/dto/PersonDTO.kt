package com.history.tree.dto

import java.time.LocalDate

data class PersonDTO(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val patronymic: String?,
        val treeId: Long,
        val gender: Char,
        val birthDate: LocalDate,
        val dateOfDeath: LocalDate?,
)