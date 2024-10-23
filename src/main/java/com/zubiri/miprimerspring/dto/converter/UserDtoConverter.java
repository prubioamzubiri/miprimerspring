package com.zubiri.miprimerspring.dto.converter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.zubiri.miprimerspring.dominio.user.Rol;
import com.zubiri.miprimerspring.dominio.user.Usuario;
import com.zubiri.miprimerspring.dto.GetUserDto;
import com.zubiri.miprimerspring.dto.UserInDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDtoConverter {

    private PasswordEncoder passwordEncoder;

    public Usuario toUser(UserInDto usuarioDTO)
    {
        Usuario respuesta = Usuario.builder().
                                username(usuarioDTO.getUsername()).
                                password(passwordEncoder.encode(usuarioDTO.getPassword())).
                                email(usuarioDTO.getEmail()).
                                roles(Stream.of(Rol.USER).collect(Collectors.toSet())).
                                build();
        

                                    

        return respuesta;
    }

    public GetUserDto fromUser(Usuario usuario)
    {
        GetUserDto respuesta = GetUserDto.builder().
                                username(usuario.getUsername()).
                                email(usuario.getEmail()).
                                roles(usuario.getRoles().toString()).
                                build();
        

                                    

        return respuesta;
    }
    
}