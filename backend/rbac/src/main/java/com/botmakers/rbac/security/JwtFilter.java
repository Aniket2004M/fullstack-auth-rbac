package com.botmakers.rbac.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

        String path=httpRequest.getRequestURI();

        if(path.startsWith("/api/public")){
            chain.doFilter(request,response);
        }

        String authHeader=httpRequest.getHeader("Authorization");

        //if header have Bearer token
        if(authHeader != null && authHeader.startsWith("Bearer")){

            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)){
              //  SecurityContextHolder.getContext().setAuthentication(null);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        chain.doFilter(request,response);
    }
}
