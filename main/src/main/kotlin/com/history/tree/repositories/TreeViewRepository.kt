package com.history.tree.repositories

import com.history.tree.model.TreeView
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TreeViewRepository : CoroutineCrudRepository<TreeView, UUID>