package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubiri.miprimerspring.aplicacion.AplicacionUsuario;
import com.zubiri.miprimerspring.dto.userdtos.UserGetDto;
import com.zubiri.miprimerspring.dto.userdtos.UserRegisterDto;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
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
    
    
}
