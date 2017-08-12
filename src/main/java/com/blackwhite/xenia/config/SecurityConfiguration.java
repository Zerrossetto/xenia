package com.blackwhite.xenia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.HttpSecurity;
import org.springframework.security.core.userdetails.MapUserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {

    @Bean
    UserDetailsRepository userDetailsRepository() {
        return new MapUserDetailsRepository(
                User.withUsername("jlong").roles("USER").password("password").build(),
                User.withUsername("rwinch").roles("ADMIN", "USER").password("password").build());
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(HttpSecurity security) {

    	return security
    			.authorizeExchange().pathMatchers("/public/**").permitAll()
    			.anyExchange().authenticated()
    			.and().build();
    }
}
