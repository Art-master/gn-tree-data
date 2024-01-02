package com.history.tree.repositories

import com.history.tree.model.Tree
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TreeRepository : CoroutineCrudRepository<Tree, UUID> {
    suspend fun findByUserId(userId: String)

}