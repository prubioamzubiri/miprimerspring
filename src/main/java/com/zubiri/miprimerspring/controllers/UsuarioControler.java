package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubiri.miprimerspring.dto.userdtos.UserGetDto;
import com.zubiri.miprimerspring.dto.userdtos.UserRegisterDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuarios")
public class UsuarioControler {

    @PostMapping("/registrar")
    public ResponseEntity<UserGetDto> Registrar(@RequestBody UserRegisterDto entity) {
        //TODO: process POST request
        
        return null;
    }
    
    
}
