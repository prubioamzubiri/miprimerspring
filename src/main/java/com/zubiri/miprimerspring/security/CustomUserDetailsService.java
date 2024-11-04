package com.zubiri.miprimerspring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zubiri.miprimerspring.dominio.usuario.Usuario;
import com.zubiri.miprimerspring.persistencia.RepositorioUsuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final RepositorioUsuario userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        

        Usuario user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return user;
        
    }


    
}
