package com.history.tree.repositories

import com.history.tree.model.Marriage
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MarriageRepository : CoroutineCrudRepository<Marriage, UUID> {
    suspend fun findAllByTreeId(treeId: UUID): Flow<Marriage>
}