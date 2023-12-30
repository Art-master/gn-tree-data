package com.history.tree.dto

import java.time.LocalDate
import java.util.*

data class PersonDto(
        val id: UUID,
        val treeViewId: UUID,
        val firstName: String,
        val lastName: String,
        val patronymic: String?,
        val gender: Char,
        val birthDate: LocalDate,
        val dateOfDeath: LocalDate?
)