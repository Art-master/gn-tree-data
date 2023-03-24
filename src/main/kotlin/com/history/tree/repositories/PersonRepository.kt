package com.history.tree.repositories

import com.history.tree.model.Person
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonRepository : CoroutineCrudRepository<Person, UUID> {

    suspend fun findAllByTreeId(treeId: UUID): Flow<Person>
}