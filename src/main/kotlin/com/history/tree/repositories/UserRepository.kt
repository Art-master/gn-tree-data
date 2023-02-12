package com.history.tree.repositories

import com.history.tree.schemas.UserSchema.LOGIN
import com.history.tree.schemas.UserSchema.TABLE_NAME
import com.history.tree.model.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CoroutineCrudRepository<User, Long> {

    @Query("SELECT * FROM $TABLE_NAME WHERE $LOGIN=$1 LIMIT 1")
    suspend fun findByLogin(login: String): User?
}
