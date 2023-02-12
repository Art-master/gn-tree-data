package com.history.tree.repositories

import com.history.tree.model.Tree
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TreeRepository : CoroutineCrudRepository<Tree, Long> {

    fun removeById(id: Long): Long
}