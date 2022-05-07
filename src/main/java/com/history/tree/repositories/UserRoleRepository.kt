package com.history.tree.repositories

import com.history.tree.model.UserRole
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface UserRoleRepository : CoroutineCrudRepository<UserRole, Long> {

    suspend fun findByUserId(userId: Long): Flux<UserRole>
}
