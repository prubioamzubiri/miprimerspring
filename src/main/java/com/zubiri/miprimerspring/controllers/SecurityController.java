package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("")
    public String getMethodName() {
        
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }
    
    
}
