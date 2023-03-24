package com.history.tree.services

import com.history.tree.dto.PersonViewDTO
import com.history.tree.mappers.PersonViewMapper
import com.history.tree.repositories.PersonViewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonViewService(val repository: PersonViewRepository, val mapper: PersonViewMapper) {
    suspend fun findById(id: UUID): PersonViewDTO? {
        val personView = repository.findById(id)
        personView ?: return null
        return mapper.entityToDTO(personView)
    }

    suspend fun getPersonViewsByTreeId(treeId: UUID): Flow<PersonViewDTO> {
        return repository.findAllByTreeId(treeId)
            .map { personView -> mapper.entityToDTO(personView) }
    }
}