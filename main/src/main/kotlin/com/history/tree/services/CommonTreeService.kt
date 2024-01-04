package com.history.tree.services

import com.history.tree.mappers.EntityMapper
import com.history.tree.repositories.CommonTreeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

/**
 * @param E entity type
 * @param D dto type
 */
open class CommonTreeService<E, D>(
    private val repository: CommonTreeRepository<E>,
    private val mapper: EntityMapper<E, D>
) {
    open suspend fun findById(id: UUID): D? {
        val entity = repository.findById(id)
        entity ?: return null
        return mapper.entityToDto(entity)
    }

    open suspend fun getByTreeViewId(treeId: UUID): Flow<D> {
        return repository.findAllByTreeViewId(treeId).map { entity -> mapper.entityToDto(entity) }
    }

    open suspend fun delete(id: UUID) {
        return repository.deleteById(id)
    }

    open suspend fun deleteByTreeViewId(treeViewId: UUID) {
        return repository.deleteByTreeViewId(treeViewId)
    }

    open suspend fun edit(entityDto: D): D {
        val entity: E = mapper.dtoToEntity(entityDto)
        val saved = repository.save(entity)
        return mapper.entityToDto(saved)
    }

    open suspend fun saveAll(entities: List<D>) {
        val entitiesModels: List<E> = entities.map { mapper.dtoToEntity(it) }
        repository.saveAll(entitiesModels)
    }
}