package com.history.tree.mappers

import com.history.tree.dto.TreeViewDto
import com.history.tree.model.TreeView
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TreeViewMapper : EntityMapper<TreeView, TreeViewDto> {

    @Mapping(source = "treeId", target = "treeId")
    @Mapping(target = "isMain", constant = "true")
    fun dtoToEntity(dto: TreeViewDto, treeId: Long): TreeView
}