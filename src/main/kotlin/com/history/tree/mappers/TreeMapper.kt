package com.history.tree.mappers

import com.history.tree.dto.TreeDto
import com.history.tree.model.Tree
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TreeMapper : EntityMapper<Tree, TreeDto>