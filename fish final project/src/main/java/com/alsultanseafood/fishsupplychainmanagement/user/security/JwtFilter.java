package com.alsultanseafood.fishsupplychainmanagement.user.security;



import com.alsultanseafood
.fishsupplychainmanagement
.user.service.UserService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.
        UserDetails;

import org.springframework.security.core.context.
        SecurityContextHolder;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.
        OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter
        extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserService userService;

    public JwtFilter(
            JwtUtil jwtUtil,
            UserService userService) {

        this.jwtUtil =
                jwtUtil;

        this.userService =
                userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException,
            IOException {

        String header =
                request.getHeader(
                        "Authorization");

        if (header != null
                && header.startsWith(
                "Bearer ")) {

            String token =
                    header.substring(7);

            try {



                if (
        jwtUtil.isTokenExpired(
                token
        )
) {
    filterChain.doFilter(
            request,
            response
    );

    return;
}
    String email = jwtUtil.extractEmail(token);

    if (email != null &&
        SecurityContextHolder.getContext()
            .getAuthentication() == null) {

        UserDetails user =
                userService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities());

       auth.setDetails(
        new WebAuthenticationDetailsSource()
                .buildDetails(request)
);

SecurityContextHolder
        .getContext()
        .setAuthentication(auth);
    }
}
catch (Exception e) {
    filterChain.doFilter(request, response);
    return;
}
        }

        filterChain.doFilter(
                request,
                response);
    }
}