package com.history.tree.mappers

import org.mapstruct.InheritInverseConfiguration

interface EntityMapper<ENTITY, DTO> {
    fun entityToDTO(entity: ENTITY): DTO

    @InheritInverseConfiguration
    fun dtoToEntity(dto: DTO): ENTITY
}