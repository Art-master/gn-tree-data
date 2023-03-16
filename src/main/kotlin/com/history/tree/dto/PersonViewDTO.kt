package com.history.tree.dto

data class PersonViewDTO(
    val id: Long,
    val coordinate_x: Float,
    val coordinate_y: Float,
    val personId: Long,
    val treeId: Long
)