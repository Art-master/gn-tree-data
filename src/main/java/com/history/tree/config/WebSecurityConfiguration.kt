package com.history.tree.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import java.util.stream.Collectors


@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfiguration {

    private val log: Logger = LoggerFactory.getLogger(WebSecurityConfiguration::class.java.name)

    // For MVC use WebSecurityConfigurerAdapter
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
        http.authorizeExchange { authorizeRequests ->
                authorizeRequests
                    .pathMatchers("/swagger/**").permitAll()
                    .pathMatchers("/actuator/**").permitAll()
                    .pathMatchers("/auth/**").permitAll()
                    .pathMatchers("/debug/**").permitAll()
                    .anyExchange().authenticated()
                    //.anyExchange().permitAll() //DEBUG
                    .and()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .csrf().disable()
            }

        return http.build()
    }

    @Bean
    fun jwtAuthenticationConverter(): Converter<Jwt, out Mono<out AbstractAuthenticationToken>>? {
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesExtractor())
        return ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter)
    }

    @Bean
    fun jwtGrantedAuthoritiesExtractor(): Converter<Jwt, Collection<GrantedAuthority>> {
        val delegate = JwtGrantedAuthoritiesConverter()

        return object : Converter<Jwt, Collection<GrantedAuthority>> {

            @SuppressWarnings("unchecked")
            override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
                val realmAccess = jwt.claims["realm_access"] as Map<*, *>

                return (realmAccess["roles"] as List<String>?)!!.stream()
                    .map { roleName: String -> "ROLE_$roleName" }
                    .map { role: String? -> SimpleGrantedAuthority(role) }
                    .collect(Collectors.toList())
            }
        }
    }
}
