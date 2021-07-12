package com.cognizant.truyum.controller;

import java.util.HashMap;
import java.util.Map;

import com.cognizant.truyum.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


/**
 * AuthenticationController
 */
@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    /**
     * 
     * @param authHeader
     * @return token object 
     */
    @GetMapping("/getToken")
    public Map<String,String> authenticate(@RequestHeader("Authorization") String authHeader){
        log.info("START :- Token generation");

        var user = authenticationService.getUser(authHeader);
        var token = authenticationService.generateJwt(user);
        log.debug("token = {}",token);

        Map<String,String> map = new HashMap<>();
        map.put("token", token);
        log.info("END");
        
        return map;
    }
   
    
}