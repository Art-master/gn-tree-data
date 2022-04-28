package com.history.tree.mappers;

import com.history.tree.dto.MarriageDTO;
import com.history.tree.dto.RelationshipDTO;
import com.history.tree.model.Relationship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RelationshipMapper {
    RelationshipMapper INSTANCE = Mappers.getMapper(RelationshipMapper.class);

    @Mapping(target = "marriage", source = "marriage")
    RelationshipDTO relationshipToDto(Relationship relationship, MarriageDTO marriage);

    RelationshipDTO relationshipToDto(Relationship relationship);
}
