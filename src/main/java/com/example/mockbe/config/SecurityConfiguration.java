package com.example.mockbe.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.example.mockbe.model.user.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {"/api/auth/**"};

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(POST,"/api/distributor/**").hasAnyAuthority("ROLE_ADMIN","ROLE_COORDINATOR")
                                .requestMatchers(GET,"/api/distributor/**").hasAnyAuthority("ROLE_ADMIN","ROLE_COORDINATOR", "ROLE_EMPLOYEESTOCK")
                                .requestMatchers(DELETE,"/api/distributor/**").hasAnyAuthority("ROLE_ADMIN","ROLE_COORDINATOR")
                                .requestMatchers(PUT,"/api/distributor/**").hasAnyAuthority("ROLE_ADMIN","ROLE_COORDINATOR")
                                .requestMatchers(POST,"/api/products/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEESTOCK")
                                .requestMatchers(GET,"/api/products/**").hasAnyAuthority("ROLE_ADMIN","ROLE_EMPLOYEESTOCK","ROLE_COORDINATOR")
                                .requestMatchers(DELETE,"/api/products/**").hasAnyAuthority("ROLE_ADMIN","ROLE_EMPLOYEESTOCK")
                                .requestMatchers(PUT,"/api/products/**").hasAnyAuthority("ROLE_ADMIN","ROLE_EMPLOYEESTOCK")
                                .requestMatchers(POST,"/api/transcation/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_COORDINATOR")
                                .requestMatchers(GET,"/api/transcation/**").hasAnyAuthority("ROLE_ADMIN","ROLE_EMPLOYEESTOCK","ROLE_COORDINATOR")
                                .requestMatchers(DELETE,"/api/transcation/**").hasAnyAuthority("ROLE_ADMIN","ROLE_COORDINATOR")
                                .requestMatchers(PUT,"/api/transcation/**").hasAnyAuthority("ROLE_ADMIN","ROLE_COORDINATOR")


                                .anyRequest()

                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}

