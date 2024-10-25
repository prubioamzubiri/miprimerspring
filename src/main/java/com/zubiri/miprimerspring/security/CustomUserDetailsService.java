package com.zubiri.miprimerspring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zubiri.miprimerspring.dominio.user.Usuario;
import com.zubiri.miprimerspring.persistencia.RepositorioUsuario;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{


    private final RepositorioUsuario repositorioUsuario;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        Usuario to_return = repositorioUsuario.findByUsername(username).orElseThrow(() -> 
        new UsernameNotFoundException("Usuario no encontrado"));

        return to_return;
    }
    
}
