package com.cognizant.truyum.service;

import java.util.Base64;
import java.util.Date;


import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationService {

    private static final String SECRET_KEY = "secretkey";

    /**
     * 
     * @param authHeader
     * @return user name
     */
    public String getUser(String authHeader) {
        var encodedCredentials = authHeader.substring(6);
        var bytes = Base64.getDecoder().decode(encodedCredentials);
        var bytesToString = new String(bytes);
        var colonIndex = bytesToString.indexOf(":");
        var userName = bytesToString.substring(0, colonIndex);
        log.debug(userName);
        return userName;
    }

    /**
     * 
     * @param user
     * @return json web token expiry of token is 20 minutes
     */
    public String generateJwt(String user) {
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + 1200000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY);
        var token = builder.compact();
        log.debug(token);
        return token;
    }

   
}
