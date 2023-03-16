package com.history.tree.services

import com.history.tree.dto.MarriageDTO
import com.history.tree.mappers.MarriageMapper
import com.history.tree.model.Marriage
import com.history.tree.repositories.MarriageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service

@Service
class MarriageService(val repository: MarriageRepository, val mapper: MarriageMapper) {

    suspend fun findById(id: Long): MarriageDTO? {
        val marriage = repository.findById(id)
        marriage ?: return null
        return mapper.entityToDTO(marriage)
    }

    suspend fun getByTreeId(treeId: Long): Flow<MarriageDTO> {
        return repository.findMarriagesByTree(treeId).map { m -> mapper.entityToDTO(m) }
    }
}