package com.history.tree.repositories

import com.history.tree.model.PersonView
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonViewRepository : CoroutineCrudRepository<PersonView, UUID> {

    suspend fun findAllByTreeId(treeId: UUID): Flow<PersonView>
}