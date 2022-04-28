package com.history.tree.mappers;

import com.history.tree.dto.RelationshipDTO;
import com.history.tree.model.Relationship;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RelationshipMapper {
    RelationshipMapper INSTANCE = Mappers.getMapper(RelationshipMapper.class);

    RelationshipDTO relationshipToDto(Relationship relationship);
}
