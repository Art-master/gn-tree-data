package com.history.tree.services

import com.history.tree.dto.PersonDTO
import com.history.tree.mappers.PersonMapper
import com.history.tree.repositories.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class PersonService(val repository: PersonRepository) {

    suspend fun findById(id: Long): PersonDTO {
        val person = repository.findById(id)

        val mapper = Mappers.getMapper(PersonMapper::class.java)

        return mapper.personToDto(person!!)
    }

    suspend fun getPersonsByTreeId(treeId: Long): Flow<PersonDTO> {
        val mapper = Mappers.getMapper(PersonMapper::class.java)

        return repository.findAllByTreeId(treeId)
            .map { person -> mapper.personToDto(person) }
    }
}