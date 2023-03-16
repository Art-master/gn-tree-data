package com.history.tree.repositories

import com.history.tree.model.RelatedPoints
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RelatedPointsRepository : CoroutineCrudRepository<RelatedPoints, Long> {

    suspend fun findAllByTreeId(treeId: Long): Flow<RelatedPoints>
}