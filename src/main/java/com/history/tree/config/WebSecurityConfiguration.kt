package com.history.tree.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.*
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfiguration {

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private lateinit var issuerUri: String

    // For MVC need to use WebSecurityConfigurerAdapter
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter())
            .jwtDecoder(jwtDecoder())

        http.authorizeExchange { authorizeRequests ->
            authorizeRequests
                .pathMatchers("/swagger/**").permitAll()
                .pathMatchers("/actuator/**").permitAll()
                .pathMatchers("/debug/**").permitAll()
                .anyExchange().permitAll() //TODO
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
        }

        return http.build()
    }

    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder {
        val jwtSetUri = "${this.issuerUri}/protocol/openid-connect/certs"
        return NimbusReactiveJwtDecoder.withJwkSetUri(jwtSetUri).build()
    }


    @Bean
    fun jwtAuthenticationConverter(): ReactiveJwtAuthenticationConverter? {
        val authoritiesConverter = JwtGrantedAuthoritiesConverter()
        authoritiesConverter.setAuthorityPrefix("ROLE_")
        authoritiesConverter.setAuthoritiesClaimName("roles")

        val jwtAuthenticationConverter = ReactiveJwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
            ReactiveJwtGrantedAuthoritiesConverterAdapter(authoritiesConverter)
        )

        return jwtAuthenticationConverter
    }
}
