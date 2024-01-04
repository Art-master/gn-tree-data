package com.history.tree.mappers

import com.history.tree.dto.TreeViewDto
import com.history.tree.model.TreeView
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TreeViewMapper : EntityMapper<TreeView, TreeViewDto> {

    @Mapping(source = "treeId", target = "treeId")
    fun dtoToEntity(dto: TreeViewDto, treeId: Long): TreeView
}