package com.rdms.config;

import antlr.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    AuthenticationFilter(final RequestMatcher requiresAuth) {
        super(requiresAuth);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Optional tokenParam = Optional.ofNullable(request.getHeader(AUTHORIZATION)); //Authorization: Bearer TOKEN
        Optional pss = Optional.ofNullable(request.getHeader("userName")); //Authorization: Bearer TOKEN
        if(tokenParam.isEmpty() || pss.isEmpty()  ){
            return null;
        }
        String userName = request.getHeader("userName");
        String token = request.getHeader(AUTHORIZATION);
        token =token.replaceAll("Bearer","").trim();
        Authentication requestAuthentication = new UsernamePasswordAuthenticationToken(userName.toLowerCase(), token);
        return getAuthenticationManager().authenticate(requestAuthentication);
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
