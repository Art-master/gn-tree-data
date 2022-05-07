package com.history.tree.services

import com.history.tree.repositories.UserRepository
import com.history.tree.repositories.UserRoleRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val userRoleRepository: UserRoleRepository
) : ReactiveUserDetailsService {

    override fun findByUsername(username: String?): Mono<UserDetails>? {

        return Mono.create {
            GlobalScope.launch {
                val user = userRepository.findByLogin(username!!)
                if (user == null) {
                    it.error(UsernameNotFoundException("User not found by login"))
                } else {
                    val roles = userRoleRepository.findByUserId(user.id)
                    it.success(user as UserDetails)
                }
            }
        }
    }
}