package com.history.tree.repositories

import com.history.tree.model.Relationship
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RelationshipRepository : CommonTreeRepository<Relationship>, CoroutineCrudRepository<Relationship, UUID>