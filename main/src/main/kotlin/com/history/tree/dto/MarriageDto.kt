package com.history.tree.dto

import java.time.LocalDate
import java.util.*

data class MarriageDto (
    val id: UUID,
    val dateStart: LocalDate?,
    val dateEnd: LocalDate?,
    val treeViewId: UUID,
)