package com.zubiri.miprimerspring.security;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;



public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ("user".equals(username) && "1234".equals(password)) {
            
            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());

        } else {
            throw new AuthenticationCredentialsNotFoundException("Error!");
        }
  
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
    
}
