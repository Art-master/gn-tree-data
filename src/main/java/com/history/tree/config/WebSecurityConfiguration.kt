package com.history.tree.config

import com.google.gson.GsonBuilder
import com.history.tree.services.CustomUserDetailsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import java.lang.RuntimeException


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration(private val userDetailsService: CustomUserDetailsService) {

    private val log: Logger = LoggerFactory.getLogger(WebSecurityConfiguration::class.java.name)

    // For MVC use WebSecurityConfigurerAdapter
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return if (SECURITY_ENABLED) {
            http
                .authorizeExchange { authorizeRequests ->
                    authorizeRequests
                        .pathMatchers("/swagger/**").permitAll()
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers("/auth/**").permitAll()
                        .pathMatchers("/debug/**").permitAll()
                        .anyExchange().authenticated()
                        .and()
                        .formLogin().disable().httpBasic().and()
                        .csrf().disable()
                }
                .authenticationManager(reactiveAuthenticationManager())
                .oauth2ResourceServer { resourceServerConfigurer ->
                    resourceServerConfigurer
                        .jwt { jwtConfigurer ->
                            jwtConfigurer
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        }
                }
                .build()
        } else http.authorizeExchange()
            .anyExchange()
            .permitAll().and()
            .csrf().disable()
            .build()
    }

    @Bean
    protected fun reactiveAuthenticationManager(): ReactiveAuthenticationManager? {
        return ReactiveAuthenticationManager { authentication: Authentication ->
            try {
                checkUser(authentication).doOnError {
                    log.error(it.message)
                }

            } catch (e: UsernameNotFoundException) {
                Mono.error(e)
            }
        }
    }

    private fun checkUser(authentication: Authentication): Mono<Authentication> {
        val login = authentication.principal as CharSequence
        val credentials = authentication.credentials as CharSequence

        if (login.isEmpty()) throw RuntimeException("Login must be not empty")
        if (credentials.isEmpty()) throw RuntimeException("Password must be not empty")

        val encodedCredentials = passwordEncoder().encode(credentials)

        return userDetailsService.findByUsername(authentication.principal as String)!!.map {
            if (it.password != encodedCredentials) {
                throw RuntimeException("Incorrect password")
            }
            UsernamePasswordAuthenticationToken(it.username, "", it.authorities)
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
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
            override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
                val grantedAuthorities: MutableCollection<GrantedAuthority> = delegate.convert(jwt)!!
                if (jwt.getClaim<Jwt>("realm_access") == null) {
                    return grantedAuthorities
                }
                val realmAccess: JSONObject = jwt.getClaim("realm_access")
                if (realmAccess.get("roles") == null) {
                    return grantedAuthorities
                }
                val gson = GsonBuilder().create()
                val roles = realmAccess.getString("roles")
                val list = gson.fromJson(roles, Array<SimpleGrantedAuthority>::class.java).toList()
                val keycloakAuthorities: List<SimpleGrantedAuthority> = list.map { role ->
                    SimpleGrantedAuthority("ROLE_$role")
                }
                grantedAuthorities.addAll(keycloakAuthorities)
                return grantedAuthorities
            }
        }
    }

    companion object {
        private const val SECURITY_ENABLED = true //DANGER
    }
}