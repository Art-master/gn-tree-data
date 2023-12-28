package com.history.tree.dto

import com.history.tree.model.Marriage
import com.history.tree.model.Relationship

data class FullTreeDataDto(
    val persons: List<PersonDto>,
    val personViews: List<PersonViewDto>,
    val linkedEdges: List<PersonLinkedEdgesDto>,
    val relationships: List<Relationship>,
    val marriageList: List<Marriage>,
)