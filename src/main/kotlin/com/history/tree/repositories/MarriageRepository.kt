package com.history.tree.repositories

import com.history.tree.model.Marriage
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MarriageRepository : CoroutineCrudRepository<Marriage, Long> {
    suspend fun findAllByTreeId(treeId: Long): Flow<Marriage>
}