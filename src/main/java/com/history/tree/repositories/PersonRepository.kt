package com.history.tree.repositories

import com.history.tree.model.Person
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CoroutineCrudRepository<Person, Long> {

    suspend fun findAllByTreeId(treeId: Long): Flow<Person>
}