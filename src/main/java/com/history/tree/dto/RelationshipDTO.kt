package com.history.tree.dto

data class RelationshipDTO(
        val id: Long,
        val marriage: MarriageDTO? = null,
        val relationPersonId: Long? = null,
        val relationshipType: Int = 0,
)