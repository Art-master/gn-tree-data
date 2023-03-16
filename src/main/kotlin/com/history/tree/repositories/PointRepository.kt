package com.history.tree.repositories

import com.history.tree.model.Point
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PointRepository : CoroutineCrudRepository<Point, Long> {

    suspend fun findAllByTreeId(treeId: Long): Flow<Point>
}