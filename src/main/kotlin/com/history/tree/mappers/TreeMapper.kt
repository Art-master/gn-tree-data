package com.history.tree.mappers

import com.history.tree.dto.TreeDTO
import com.history.tree.model.Tree
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TreeMapper : EntityMapper<Tree, TreeDTO>