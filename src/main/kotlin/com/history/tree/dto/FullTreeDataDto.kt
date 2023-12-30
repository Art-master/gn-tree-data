package com.history.tree.dto

data class FullTreeDataDto(
    val persons: List<PersonDto>,
    val personViews: List<PersonViewDto>,
    val edges: List<EdgeDto>,
    val viewsLinks: List<ViewsLinkDto>,
    val relationships: List<RelationshipDto>
)