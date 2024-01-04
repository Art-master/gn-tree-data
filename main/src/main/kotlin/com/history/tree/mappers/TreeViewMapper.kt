package com.history.tree.mappers

import com.history.tree.dto.TreeViewDto
import com.history.tree.model.TreeView
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TreeViewMapper : EntityMapper<TreeView, TreeViewDto>