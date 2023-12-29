package com.history.tree.dto

data class FullTreeDataDto(
    val persons: List<PersonDto>,
    val personViews: List<PersonViewDto>,
    val edges: List<EdgeDto>,
    val viewsLinks: List<ViewsLinksDto>,
    val relationships: List<RelationshipDto>,
    val marriageList: List<MarriageDto>,
)