package com.history.tree.repositories

import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * Common repository interface
 * @param E entity type
 */
interface CommonTreeRepository<E> {
    suspend fun findById(id: UUID): E?
    suspend fun findAllByTreeViewId(treeViewId: Long): Flow<E>
    suspend fun deleteById(id: UUID)
    suspend fun deleteByTreeViewId(treeViewId: Long)
    suspend fun <S : E> save(entity: S): E
    suspend fun <S : E> saveAll(entities: List<S>): List<E>
}