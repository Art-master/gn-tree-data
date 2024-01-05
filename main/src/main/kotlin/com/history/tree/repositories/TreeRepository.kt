package com.history.tree.repositories

import com.history.tree.model.Tree
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TreeRepository : CoroutineCrudRepository<Tree, Long> {
    suspend fun findByUserId(userId: Long): Flow<Tree>

}