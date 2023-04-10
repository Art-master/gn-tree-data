package com.history.tree.services

import com.history.tree.dto.MarriageDTO
import com.history.tree.mappers.MarriageMapper
import com.history.tree.model.Marriage
import com.history.tree.repositories.MarriageRepository
import org.springframework.stereotype.Service

@Service
class MarriageService(val repository: MarriageRepository, mapper: MarriageMapper) :
    CommonTreeService<Marriage, MarriageDTO>(repository, mapper)