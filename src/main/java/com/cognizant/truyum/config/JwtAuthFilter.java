package com.cognizant.truyum.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Collections;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthFilter extends BasicAuthenticationFilter {

    /**
     * 
     * @param authenticationManager
     */
    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        log.debug("{}:",authenticationManager);
    }


    /**
     * @param request
     * @param response
     * @param filterchain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("Basic auth filter");
        String header = request.getHeader("Authorization");
        log.debug("Header: {}", header);

        if(header == null || !header.startsWith("Bearer ")){
            chain.doFilter(request, response);
            return;
        }

        var authentication=getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
        log.info("End");
    }

     /**
     * 
     * @param request
     * @return usernamepassword authentication token
     */
    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        log.debug("OK");
        String token = request.getHeader("Authorization");
        log.debug("token= {}",token);
        if (token != null) {
            Jws<Claims> jws;
            try {
                jws = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token.replace("Bearer ", ""));

                String user = jws.getBody().getSubject();
                log.debug(user);

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                }
            } catch (JwtException e) {
                return null;
            }
            return null;
        }
        return null;
    }

}
