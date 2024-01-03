package com.history.tree.repositories

import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * Common repository interface
 * @param E entity type
 */
interface CommonTreeRepository<E> {
    suspend fun findById(id: UUID): E?
    suspend fun findAllByTreeViewId(treeViewId: UUID): Flow<E>
    suspend fun deleteById(id: UUID)
    suspend fun deleteByTreeViewId(treeViewId: UUID)
    suspend fun <S : E> save(entity: S): E
}