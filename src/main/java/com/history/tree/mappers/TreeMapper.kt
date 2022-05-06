package com.history.tree.mappers

import com.history.tree.dto.TreeDTO
import com.history.tree.model.Tree
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface TreeMapper : EntityMapper<Tree, TreeDTO> {
    companion object {
        val INSTANCE = Mappers.getMapper(TreeMapper::class.java)!!
    }
}