package com.history.tree.repositories

import com.history.tree.model.TreeView
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TreeViewRepository : CoroutineCrudRepository<TreeView, Long> {

    @Query("SELECT * FROM tree_view tv WHERE tv.tree_id=:treeId AND tv.is_main='true' LIMIT 1")
    fun findByTreeIdAndIsMainExists(@Param("treeId") treeId: Long): Flow<TreeView>

    @Query("DELETE * FROM tree_view tv WHERE tv.tree_id=:treeId")
    fun deleteAllByTreeId(@Param("treeId") treeId: Long)
}