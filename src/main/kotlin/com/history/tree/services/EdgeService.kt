package com.history.tree.services

import com.history.tree.dto.EdgeDTO
import com.history.tree.mappers.EdgeMapper
import com.history.tree.model.Edge
import com.history.tree.repositories.EdgeRepository

class EdgeService(val repository: EdgeRepository, val mapper: EdgeMapper) :
    CommonTreeService<Edge, EdgeDTO>(repository, mapper)