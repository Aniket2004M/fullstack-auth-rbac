package com.botmakers.rbac.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilter {

    private final JwtUtil jwtUtil;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest) request;

        String authHeader=httpRequest.getHeader("Authorization");

        //if header have Bearer token
        if(authHeader != null && authHeader.startsWith("Bearer")){
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)){
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }
        chain.doFilter(request,response);
    }
}
