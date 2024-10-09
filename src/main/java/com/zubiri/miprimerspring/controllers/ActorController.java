package com.zubiri.miprimerspring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubiri.miprimerspring.aplicacion.IAplicacion;
import com.zubiri.miprimerspring.dominio.Actor;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping("/addActor")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
  
        try{
            if(aplicacionActores.guardar(actor))
            {
                return ResponseEntity.status(HttpStatus.CREATED)
                .body(actor);  
            }
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(null);
            }

        }
        catch(Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(null);
            
        }
   
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActorById(@PathVariable Integer id)
    {
        if(aplicacionActores.eliminar(id))
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                                 .body(null);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(null);
        }
    }



    
    
}
