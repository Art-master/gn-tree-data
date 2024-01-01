package com.history.tree.services

import com.history.tree.mappers.EntityMapper
import com.history.tree.repositories.CommonTreeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

open class CommonTreeService<ENTITY, DTO>(
    private val repository: CommonTreeRepository<ENTITY>,
    private val mapper: EntityMapper<ENTITY, DTO>
) {
    suspend fun findById(id: UUID): DTO? {
        val entity = repository.findById(id)
        entity ?: return null
        return mapper.entityToDTO(entity)
    }

    open suspend fun getByTreeViewId(treeId: UUID): Flow<DTO> {
        return repository.findAllByTreeViewId(treeId).map { entity -> mapper.entityToDTO(entity) }
    }
    
    suspend fun delete(id: UUID) {
        return repository.deleteById(id)
    }

    suspend fun edit(entityDTO: DTO): DTO {
        val entity: ENTITY = mapper.dtoToEntity(entityDTO)
        val saved = repository.save(entity)
        return mapper.entityToDTO(saved)
    }
}