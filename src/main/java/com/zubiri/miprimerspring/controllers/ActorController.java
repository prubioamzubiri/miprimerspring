package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubiri.miprimerspring.aplicacion.IAplicacion;
import com.zubiri.miprimerspring.dominio.Actor;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/actor")
@AllArgsConstructor
public class ActorController {

    IAplicacion<Actor> aplicacionActores;

    @GetMapping("/{id}")
    public Actor buscarActor(@PathVariable Integer id)
    {
        return aplicacionActores.buscar(id);
    }
    
}
