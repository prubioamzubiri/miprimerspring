package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/security")
@EnableMethodSecurity
public class SecurityController {

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public String getMethodName() {
        
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public String getMethodName2() {
        
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
    
    
}
