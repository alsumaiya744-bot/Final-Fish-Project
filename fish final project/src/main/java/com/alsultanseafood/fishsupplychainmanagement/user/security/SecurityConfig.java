package com.alsultanseafood.fishsupplychainmanagement.user.security;



import com.alsultanseafood
.fishsupplychainmanagement
.user.service.UserService;

import org.springframework.context.annotation.*;

import org.springframework.security.authentication.
        AuthenticationManager;

import org.springframework.security.authentication.
        AuthenticationProvider;

import org.springframework.security.authentication.dao.
        DaoAuthenticationProvider;

import org.springframework.security.config.annotation.
        authentication.configuration.
        AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.
        builders.HttpSecurity;

import org.springframework.security.config.annotation.web.
        configuration.EnableWebSecurity;

import org.springframework.security.config.http.
        SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.
        BCryptPasswordEncoder;

import org.springframework.security.crypto.password.
        PasswordEncoder;

import org.springframework.security.web.
        SecurityFilterChain;

import org.springframework.security.web.authentication.
        UsernamePasswordAuthenticationFilter;
        import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain
    filterChain(
            HttpSecurity http,
            JwtFilter jwtFilter,
            AuthenticationProvider provider)
            throws Exception {

       http
        .cors(cors -> {})
        .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(
                        auth ->
                                auth

        .requestMatchers(
                "/api/auth/login",
                "/api/auth/register",
                 "/api/reports/pdf",
                "/uploads/**"
        ).permitAll()

        .requestMatchers(
                "/api/dashboard/**",
                "/api/customers/**",
                "/api/expenses/**",
                "/api/procurements/**",
                "/api/reports/**",
                "/api/sales/**",
                "/api/delivery/**"
        ).hasRole("ADMIN")

        .requestMatchers(
        "/api/user-home/**",
        "/api/cart/**",
        "/api/notifications/**"
)
.hasRole("USER")

.requestMatchers(
        "/api/orders/**"
)
.authenticated()

.requestMatchers(
        "/api/fishes/**"
)
.authenticated()

        .anyRequest()
        .authenticated()
)

                                       
                                        
        

                .sessionManagement(
                        session ->
                                session
                                        .sessionCreationPolicy(
                                                SessionCreationPolicy
                                                        .STATELESS))

                .authenticationProvider(
                        provider)

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
public AuthenticationProvider authenticationProvider(
        UserService userService,
        PasswordEncoder encoder) {

    DaoAuthenticationProvider provider =
            new DaoAuthenticationProvider(userService);

    provider.setPasswordEncoder(encoder);

    return provider;
}

    @Bean
    public PasswordEncoder
    passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager
    authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config
                .getAuthenticationManager();
    }



    @Bean
public CorsConfigurationSource
corsConfigurationSource() {

    CorsConfiguration config =
            new CorsConfiguration();

   config.addAllowedOrigin("https://al-sultan-seafood.netlify.app");
config.addAllowedOrigin("http://127.0.0.1:5500");

    config.addAllowedMethod("*");

    config.addAllowedHeader("*");

    UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

    source.registerCorsConfiguration(
            "/**",
            config);

    return source;
}
}
