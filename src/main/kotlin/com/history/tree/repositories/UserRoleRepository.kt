package com.history.tree.repositories

import com.history.tree.model.UserRole
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRoleRepository : CoroutineCrudRepository<UserRole, Long> {

    suspend fun findByUserId(userId: Long): Flow<UserRole>
}
