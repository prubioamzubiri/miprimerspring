package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubiri.miprimerspring.aplicacion.AplicacionUsuario;
import com.zubiri.miprimerspring.dominio.usuario.Usuario;
import com.zubiri.miprimerspring.dto.userdtos.UserGetDto;
import com.zubiri.miprimerspring.dto.userdtos.UserRegisterDto;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
@EnableMethodSecurity
public class UsuarioControler {

    AplicacionUsuario aplicacionUsuario;

    @PostMapping("/registrar")
    public ResponseEntity<UserGetDto> Registrar(@RequestBody UserRegisterDto entity) {

        UserGetDto toreturn = aplicacionUsuario.guardar(entity);

        if(toreturn != null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).
                                  body(toreturn);     
        }
        else
        {
            return ResponseEntity.badRequest().body(null);
        }


    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public String getMethodName(@AuthenticationPrincipal Usuario user) {
        

        String toreturn;

        toreturn = "Hola " + user.getUsername() + " con email " + user.getEmail() + " con id " + user.getId();
                            
        return toreturn;
        //return "Hola mundo";
    }

    @GetMapping("/me2")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String getMethodName2() {
        

        Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String toreturn;

        toreturn = "Hola " + user.getUsername() + " con email " + user.getEmail() + " con id " + user.getId();
                            
        return toreturn;
        //return "Hola mundo";
    }
    
    
    
}
