package com.history.tree.dto

import java.util.UUID

data class ViewsLinksDto(
    val id: UUID,
    val viewId: UUID,
    val linkedViewId: UUID,
    val treeId: UUID,
)
