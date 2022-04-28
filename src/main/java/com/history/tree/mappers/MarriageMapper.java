package com.history.tree.mappers;

import com.history.tree.dto.MarriageDTO;
import com.history.tree.model.Marriage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MarriageMapper {
    MarriageMapper INSTANCE = Mappers.getMapper(MarriageMapper.class);

    MarriageDTO relationshipToDto(Marriage marriage);
}
