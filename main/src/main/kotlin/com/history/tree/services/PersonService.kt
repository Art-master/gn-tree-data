package com.history.tree.services

import com.history.tree.dto.PersonDto
import com.history.tree.mappers.PersonMapper
import com.history.tree.model.Person
import com.history.tree.repositories.PersonRepository
import org.springframework.data.r2dbc.core.FluentR2dbcOperations
import org.springframework.data.r2dbc.core.usingAndAwait
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PersonService(
    val repository: PersonRepository, val mapper: PersonMapper,
    val op: FluentR2dbcOperations
) : CommonTreeService<Person, PersonDto>(repository, mapper) {

    suspend fun create(person: PersonDto): PersonDto {
        val entity: Person = mapper.dtoToEntity(person)
        val saved = op.insert(entity.javaClass).usingAndAwait(entity)
        return mapper.entityToDto(saved)
    }

    suspend fun deleteByTreeViewIdIfViewsNotFound(id: Long) {
        repository.deleteByTreeViewIdIfViewsNotFound(id)
    }
}