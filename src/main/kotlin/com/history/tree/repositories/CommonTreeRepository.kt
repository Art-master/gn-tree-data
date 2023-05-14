package com.history.tree.repositories

import kotlinx.coroutines.flow.Flow
import java.util.*

interface CommonTreeRepository<ENTITY> {
    suspend fun findById(id: UUID): ENTITY?
    suspend fun findAllByTreeId(treeId: UUID): Flow<ENTITY>
    suspend fun deleteById(id: UUID)
    suspend fun <S : ENTITY> save(entity: S): ENTITY
}