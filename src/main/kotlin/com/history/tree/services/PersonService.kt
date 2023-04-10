package com.history.tree.services

import com.history.tree.dto.PersonDTO
import com.history.tree.mappers.PersonMapper
import com.history.tree.model.Person
import com.history.tree.repositories.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(val repository: PersonRepository, val mapper: PersonMapper) :
    CommonTreeService<Person, PersonDTO>(repository, mapper)