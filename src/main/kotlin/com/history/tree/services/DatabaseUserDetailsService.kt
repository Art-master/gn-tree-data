package com.history.tree.services

import com.history.tree.mappers.UserMapper
import com.history.tree.repositories.UserRepository
import com.history.tree.repositories.UserRoleRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.mapstruct.factory.Mappers
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class DatabaseUserDetailsService(
    private val userRepository: UserRepository,
    private val userRoleRepository: UserRoleRepository
) : ReactiveUserDetailsService {

    private final val job = SupervisorJob()
    private final val scope = CoroutineScope(Dispatchers.Default + job)

    override fun findByUsername(username: String?): Mono<UserDetails>? {

        return Mono.create {
            scope.launch {
                val user = userRepository.findByLogin(username!!)
                if (user == null) {
                    it.error(UsernameNotFoundException("User not found by login"))
                } else {
                    val roles = userRoleRepository.findByUserId(user.id)
                        .map { it.name }
                        .toList()

                    val mapper = Mappers.getMapper(UserMapper::class.java)
                    val userDto = mapper.entityToDTO(user).apply {
                        this.authorities = roles
                        this.password = user.password
                    }
                    it.success(userDto as UserDetails)
                }
            }
        }
    }
}