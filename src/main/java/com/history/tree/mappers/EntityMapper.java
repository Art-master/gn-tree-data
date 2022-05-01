package com.history.tree.mappers;

import org.mapstruct.InheritInverseConfiguration;

public interface EntityMapper<ENTITY, DTO> {

    DTO entityToDTO(ENTITY entity);

    @InheritInverseConfiguration
    ENTITY dtoToEntity(DTO dto);
}
