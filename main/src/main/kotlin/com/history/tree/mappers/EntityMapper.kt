package com.history.tree.mappers

import org.mapstruct.InheritInverseConfiguration

/**
 * @param E entity type
 * @param D dto type
 */
interface EntityMapper<E, D> {
    fun entityToDto(entity: E): D

    @InheritInverseConfiguration
    fun dtoToEntity(dto: D): E
}