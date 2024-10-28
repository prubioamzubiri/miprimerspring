package com.zubiri.miprimerspring.dto.userdtos;

import com.zubiri.miprimerspring.dominio.usuario.UserRoles;
import com.zubiri.miprimerspring.dominio.usuario.Usuario;

import lombok.AllArgsConstructor;

import java.util.stream.*;

import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class UserDtoConverter {

    PasswordEncoder passwordEncoder;

    public Usuario toUser(UserRegisterDto user) {
        return Usuario.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .roles(Stream.of(UserRoles.USER).collect(Collectors.toSet()))
                .build();
    }

    public UserGetDto toUserGetDto(Usuario user) {
        return UserGetDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRoles().toString())
                .build();
    }
    
}
