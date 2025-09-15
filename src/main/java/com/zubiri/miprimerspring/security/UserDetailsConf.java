package com.zubiri.miprimerspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.zubiri.miprimerspring.persistencia.RepositorioUsuario;

@Configuration
public class UserDetailsConf {

    @Bean
    public UserDetailsService customUserDetailsService(RepositorioUsuario repositorioUsuario) {
        return new CustomUserDetailsService(repositorioUsuario);
    }
    
}
