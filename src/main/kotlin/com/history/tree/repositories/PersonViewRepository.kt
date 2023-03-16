package com.history.tree.repositories

import com.history.tree.model.PersonView
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonViewRepository : CoroutineCrudRepository<PersonView, Long> {

    suspend fun findAllByTreeId(treeId: Long): Flow<PersonView>
}