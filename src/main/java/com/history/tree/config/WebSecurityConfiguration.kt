package com.history.tree.config

import com.google.gson.GsonBuilder
import com.history.tree.services.DatabaseUserDetailsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService
import org.springframework.security.oauth2.client.registration.ClientRegistrations
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono


@Configuration
@ConditionalOnProperty(name = ["spring.security.oauth2.resourceserver.jwt.issuer-uri"])
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfiguration(private val userDetailsService: DatabaseUserDetailsService) {

    private val log: Logger = LoggerFactory.getLogger(WebSecurityConfiguration::class.java.name)

    // For MVC use WebSecurityConfigurerAdapter
/*    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.oauth2Client()

        return http.build()
    }*/

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.cors().and()
            .authorizeExchange { authorizeRequests ->
                authorizeRequests
                    .pathMatchers("/swagger/**").permitAll()
                    .pathMatchers("/actuator/**").permitAll()
                    .pathMatchers("/auth/**").permitAll()
                    .pathMatchers("/debug/**").permitAll()
                    .anyExchange().authenticated()
                    .and()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .csrf().disable().cors()
                //.cors().disable()
            }
        //.authenticationManager(reactiveAuthenticationManager())
        http.oauth2Client()

        return http.build()
    }

    @Bean
    fun clientRegistrations(): ReactiveClientRegistrationRepository? {
        val clientRegistration = ClientRegistrations
            .fromOidcIssuerLocation("http://localhost:8484/auth/realms/my_realm")
            .clientId("my_client")
            .clientSecret("lKYzJOZYEfiGF6U7rcLZjgHPytRvdwPA")
            .build()
        return InMemoryReactiveClientRegistrationRepository(clientRegistration)
    }

    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder? {
        return ReactiveJwtDecoders.fromIssuerLocation("http://localhost:8484/auth/realms/my_realm")
    }


    @Bean
    fun oidcUserService(): OAuth2UserService<OidcUserRequest?, OidcUser>? {
        val delegate = OidcUserService()
        return OAuth2UserService { userRequest: OidcUserRequest? ->
            val oidcUser = delegate.loadUser(userRequest)
            val claims = oidcUser.claims
            val groups = claims["groups"] as String?

            val gson = GsonBuilder().create()
            val list = gson.fromJson(groups, Array<SimpleGrantedAuthority>::class.java).toList()
            val keycloakAuthorities: List<SimpleGrantedAuthority> = list.map { role ->
                SimpleGrantedAuthority("ROLE_$role")
            }

            DefaultOidcUser(keycloakAuthorities, oidcUser.idToken, oidcUser.userInfo)
        }
    }

    //@Bean
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

        return userDetailsService.findByUsername(authentication.principal as String)!!.map {
            if (passwordEncoder().matches(credentials, it.password).not()) {
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
