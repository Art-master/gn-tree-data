package com.history.tree.mappers;

import com.history.tree.dto.TreeDTO;
import com.history.tree.model.Tree;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TreeMapper extends EntityMapper<Tree, TreeDTO> {
    TreeMapper INSTANCE = Mappers.getMapper(TreeMapper.class);
}
