package com.history.tree.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer


@Configuration
class CorsGlobalConfiguration : WebFluxConfigurer {

    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedMethods("GET", "POST", "PATCH", "DELETE")
                .allowedOrigins("*")
                .maxAge(3600)
    }

    @Bean
    fun corsFilter(@Value("\${app.cors.allowed-origins}") allowedOrigins: List<String?>?): CorsWebFilter? {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.allowedOriginPatterns = allowedOrigins
        config.addAllowedMethod("*")
        config.addAllowedHeader("*")
        source.registerCorsConfiguration("/**", config)
        return CorsWebFilter(source)
    }}