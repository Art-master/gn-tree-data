package com.history.tree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class WebSecurityConfig {

    private static final boolean SECURITY_ENABLED = false; //DANGER

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        if (SECURITY_ENABLED) {
            return http
                    .authorizeExchange()
                    .pathMatchers("/actuator/**").permitAll()
                    .pathMatchers("/auth/**").permitAll()
                    .pathMatchers("/debug/**").permitAll()
                    .anyExchange().authenticated()
                    .and()
                    .formLogin().disable().httpBasic().and()
                    .csrf().disable()
                    .build();

        } else return http.authorizeExchange()
                .anyExchange()
                .permitAll().and()
                .csrf().disable()
                .build();
    }
}
