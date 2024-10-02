package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubiri.miprimerspring.aplicacion.IAplicacionPeliculas;
import com.zubiri.miprimerspring.dominio.Actor;
import com.zubiri.miprimerspring.dominio.Director;
import com.zubiri.miprimerspring.dominio.Pelicula;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private IAplicacionPeliculas aplicacionPeliculas;

    public PeliculaController(IAplicacionPeliculas aplicacionPeliculas)
    {
        this.aplicacionPeliculas = aplicacionPeliculas;
    }

    @GetMapping("/insertData")
    public String getMethodName() {

        Director director = new Director("Francis Ford Coppola", 1000000);

        List<Actor> actors = new ArrayList<Actor>();
        Actor actor1 = new Actor("Marlon Brando", 1000000, 2),
              actor2 = new Actor("Al Pacino", 1000000, 2),
              actor3 = new Actor("Robert De Niro", 1000000, 2),
              actor4 = new Actor("Andy García", 1000000, 2);

        actors.add(actor1);
        actors.add(actor2);
        actors.add(actor3);
        actors.add(actor4);
        
        Pelicula pelicula1 = new Pelicula("El padrino", 1972, director, actors),
                 pelicula2 = new Pelicula("El padrino II", 1974, director, actors),
                 pelicula3 = new Pelicula("El padrino III", 1990, director, actors),
                 pelicula4 = new Pelicula("El padrino IV", 2022, director, actors);
        
        String nombre = aplicacionPeliculas.insertPelicula(pelicula1);
        aplicacionPeliculas.insertPelicula(pelicula2);
        aplicacionPeliculas.insertPelicula(pelicula3);
        aplicacionPeliculas.insertPelicula(pelicula4);

        return "OK, datos insertados por " + nombre;
    }

    @GetMapping("/{id}")
    public Pelicula getPeliculaById(@PathVariable int id) {
        return aplicacionPeliculas.getPelicula(id);
    }
    

    @GetMapping("")
    public List<Pelicula> getPeliculas() {
        
        return aplicacionPeliculas.getPeliculas();
    }  
    

    @GetMapping("/saludar")
    public String saludar()
    {
        return "Hola, soy el servidor";
    }

    @GetMapping("/ano/{ano}")
    public List<Pelicula> getMethodName(@PathVariable int ano) {

        return aplicacionPeliculas.getPeliculasPorAno(ano);

    }
    



    
}
