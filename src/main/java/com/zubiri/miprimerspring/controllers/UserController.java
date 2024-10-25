package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.zubiri.miprimerspring.dominio.user.Usuario;
import com.zubiri.miprimerspring.dto.GetUserDto;
import com.zubiri.miprimerspring.dto.UserInDto;
import com.zubiri.miprimerspring.dto.converter.UserDtoConverter;
import com.zubiri.miprimerspring.persistencia.RepositorioUsuario;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
@EnableMethodSecurity
public class UserController {

    UserDtoConverter userConverter;
    RepositorioUsuario repositorioUsuario;


    @PostMapping("/register")
    public ResponseEntity<GetUserDto> Register(@RequestBody UserInDto entity) {
        
        if((entity.getPassword().compareTo(entity.getPassword2())==0)
           && (entity.getEmail().compareTo(entity.getEmail2())==0))
        {

            repositorioUsuario.save(userConverter.toUser(entity));

            return ResponseEntity.status(HttpStatus.CREATED)
            .body(userConverter.fromUser(userConverter.toUser(entity)));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(null);
        } 
    }

    @RequestMapping("/me")
    @PreAuthorize("isAuthenticated()")
    GetUserDto getUserDto(@AuthenticationPrincipal Usuario user){
        return userConverter.fromUser(user);
    }
    
    
}
