package com.history.tree.services

import com.history.tree.dto.PersonDTO
import com.history.tree.mappers.PersonMapper
import com.history.tree.repositories.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(val repository: PersonRepository, val mapper: PersonMapper) {
    suspend fun findById(id: UUID): PersonDTO? {
        val person = repository.findById(id)
        person ?: return null
        return mapper.entityToDTO(person)
    }

    suspend fun getPersonsByTreeId(treeId: UUID): Flow<PersonDTO> {
        return repository.findAllByTreeId(treeId)
            .map { person -> mapper.entityToDTO(person) }
    }
}