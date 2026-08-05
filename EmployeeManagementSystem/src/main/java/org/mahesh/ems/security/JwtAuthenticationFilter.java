package org.mahesh.ems.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService,
                                   UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(


            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // Skip JWT validation for authentication endpoints
        String path = request.getServletPath();

        if (path.startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("========== JWT FILTER ==========");
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));

        final String authHeader = request.getHeader("Authorization");

        System.out.println("================================");
        System.out.println("AUTH HEADER = " + authHeader);

// IMPORTANT
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);

        System.out.println("JWT = " + jwt);

        String email;

        try {
            email = jwtService.extractEmail(jwt);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("EMAIL = " + email);

        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            System.out.println("JWT = " + jwt);
            System.out.println("Email = " + email);
            System.out.println("User = " + userDetails.getUsername());
            System.out.println("Authorities = " + userDetails.getAuthorities());
            System.out.println("Token Valid = " + jwtService.isTokenValid(jwt, userDetails));

            if (jwtService.isTokenValid(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

// ADD THIS
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

// Store Authentication
                SecurityContextHolder.getContext().setAuthentication(authToken);

// Debug
                System.out.println("Authentication Stored");
                System.out.println("Stored Authentication = "
                        + SecurityContextHolder.getContext().getAuthentication());

                System.out.println("Stored Authorities = "
                        + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            }
        }

        filterChain.doFilter(request, response);
    }
}