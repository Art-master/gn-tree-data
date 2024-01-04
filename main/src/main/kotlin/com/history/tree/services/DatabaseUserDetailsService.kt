package com.history.tree.services

import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class DatabaseUserDetailsService : ReactiveUserDetailsService {

    override fun findByUsername(username: String?): Mono<UserDetails>? {
        return null
    }
}