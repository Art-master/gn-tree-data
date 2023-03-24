package com.history.tree.dto

import java.time.LocalDate
import java.util.*

data class PersonDTO(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val patronymic: String?,
        val treeId: UUID,
        val gender: Char,
        val birthDate: LocalDate,
        val dateOfDeath: LocalDate?,
)