package com.zubiri.miprimerspring.aplicacion;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.zubiri.miprimerspring.dominio.user.Rol;
import com.zubiri.miprimerspring.dominio.user.Usuario;
import com.zubiri.miprimerspring.dto.GetUserDto;
import com.zubiri.miprimerspring.dto.UserInDto;
import com.zubiri.miprimerspring.dto.converter.UserDtoConverter;
import com.zubiri.miprimerspring.persistencia.RepositorioUsuario;

import java.util.stream.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AplicacionUsuario {

     RepositorioUsuario repositorioUsuario;
     UserDtoConverter userConverter;
     PasswordEncoder passwordEncoder;

    public GetUserDto guardar(UserInDto usuario) {
        
        try{
            if((usuario.getPassword().compareTo(usuario.getPassword2())==0)
            && (usuario.getEmail().compareTo(usuario.getEmail2())==0))
            {
                Usuario user = Usuario.builder().
                                username(usuario.getUsername()).
                                password(passwordEncoder.encode(usuario.getPassword())).
                                email(usuario.getEmail()).
                                roles(Stream.of(Rol.USER).collect(Collectors.toSet())).
                                build();

                repositorioUsuario.save(user);

                return userConverter.fromUser(user);
            }
            else
            {
                return null;
            }
            
        }
        catch(Exception e){
            return null;
        }
    }

    public GetUserDto createAdmin()
    {
        Usuario user = Usuario.builder().
                        username("admin").
                        password(passwordEncoder.encode("admin")).
                        email("admin@admin.com")
                        .roles(Stream.of(Rol.ADMIN, Rol.USER).collect(Collectors.toSet()))
                        .build();

        repositorioUsuario.save(user);

        return userConverter.fromUser(user);
                    
    }
}