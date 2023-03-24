package com.history.tree.dto

import java.util.*

data class MarriageDTO (
    val id: UUID,
    val dateStart: String?,
    val dateEnd: String?,
    val treeId: UUID
)