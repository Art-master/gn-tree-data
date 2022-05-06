package com.history.tree.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
class WebSecurityConfig {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return if (SECURITY_ENABLED) {
            http
                    .authorizeExchange()
                    .pathMatchers("/actuator/**").permitAll()
                    .pathMatchers("/auth/**").permitAll()
                    .pathMatchers("/debug/**").permitAll()
                    .anyExchange().authenticated()
                    .and()
                    .formLogin().disable().httpBasic().and()
                    .csrf().disable()
                    .build()
        } else http.authorizeExchange()
                .anyExchange()
                .permitAll().and()
                .csrf().disable()
                .build()
    }

    companion object {
        private const val SECURITY_ENABLED = false //DANGER
    }
}