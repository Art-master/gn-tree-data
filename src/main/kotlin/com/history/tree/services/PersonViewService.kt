package com.history.tree.services

import com.history.tree.dto.PersonViewDTO
import com.history.tree.mappers.PersonViewMapper
import com.history.tree.model.PersonView
import com.history.tree.repositories.PersonViewRepository
import org.springframework.stereotype.Service

@Service
class PersonViewService(val repository: PersonViewRepository, val mapper: PersonViewMapper) :
    CommonTreeService<PersonView, PersonViewDTO>(repository, mapper)