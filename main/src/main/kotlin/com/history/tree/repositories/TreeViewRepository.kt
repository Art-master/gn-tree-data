package com.history.tree.repositories

import com.history.tree.model.TreeView
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TreeViewRepository : CoroutineCrudRepository<TreeView, UUID> {
    @Query("select * from tree_view tv when tv.tree_id=:treeId and tv.is_main='TRUE'")
    fun findByTreeIdAndIsMainExists(treeId: Long): TreeView
}