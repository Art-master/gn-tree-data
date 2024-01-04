package com.history.tree.mappers

import com.history.tree.dto.TreeDto
import com.history.tree.dto.TreeViewDto
import com.history.tree.model.Tree
import org.mapstruct.Context
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TreeMapper : EntityMapper<Tree, TreeDto> {
    @Mapping(source = "userId", target = "userId")
    fun dtoToEntity(dto: TreeDto, userId: Long): Tree

    @Mapping(source = "treeView", target = "mainTreeView")
    fun entityToDto(@Context entity: Tree?, treeView: TreeViewDto): TreeDto
}