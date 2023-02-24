package com.history.tree.services

import com.history.tree.dto.PersonDTO
import com.history.tree.mappers.PersonMapper
import com.history.tree.repositories.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class PersonService(val repository: PersonRepository, val mapper: PersonMapper) {

    suspend fun findById(id: Long): PersonDTO {
        val person = repository.findById(id)
        return mapper.entityToDTO(person!!)
    }

    suspend fun getPersonsByTreeId(treeId: Long): Flow<PersonDTO> {
        return repository.findAllByTreeId(treeId)
            .map { person -> mapper.entityToDTO(person) }
    }
}