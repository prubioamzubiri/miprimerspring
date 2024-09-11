package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/peliculas")
@AllArgsConstructor
public class PeliculaController {

    @GetMapping("/saludar")
    public String saludar()
    {
        return "Hola, soy el servidor";
    }

    
}
