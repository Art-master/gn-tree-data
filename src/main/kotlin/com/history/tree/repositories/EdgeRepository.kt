package com.history.tree.repositories

import com.history.tree.model.Edge
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EdgeRepository : CommonTreeRepository<Edge>, CoroutineCrudRepository<Edge, UUID>