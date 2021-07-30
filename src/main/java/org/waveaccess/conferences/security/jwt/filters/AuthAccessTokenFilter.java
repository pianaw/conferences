package org.waveaccess.conferences.security.jwt.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.waveaccess.conferences.security.jwt.auth.JwtAuthentication;
import org.waveaccess.conferences.security.jwt.auth.JwtAuthenticationProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class AuthAccessTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtAuthenticationProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("X-access-token");

        if (token != null) {
            JwtAuthentication jwtAuthentication = new JwtAuthentication(token);
            authenticationManager.authenticate(jwtAuthentication);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        }

        filterChain.doFilter(request, response);
    }
}