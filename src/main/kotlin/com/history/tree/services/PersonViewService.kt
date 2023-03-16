package com.history.tree.services

import com.history.tree.dto.PersonViewDTO
import com.history.tree.mappers.PersonViewMapper
import com.history.tree.repositories.PersonViewRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class PersonViewService(val repository: PersonViewRepository, val mapper: PersonViewMapper) {

    suspend fun findById(id: Long): PersonViewDTO? {
        val personView = repository.findById(id)
        personView ?: return null
        return mapper.entityToDTO(personView)
    }

    suspend fun getPersonViewsByTreeId(treeId: Long): Flow<PersonViewDTO> {

    }
}