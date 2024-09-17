package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubiri.miprimerspring.aplicacion.IAplicacionPeliculas;
import com.zubiri.miprimerspring.dominio.Pelicula;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(maxAge = 3600)
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
        
        Pelicula pelicula1 = new Pelicula("1", "El padrino", 1972, "Francis Ford Coppola"),
                 pelicula2 = new Pelicula("2", "El padrino II", 1974, "Francis Ford Coppola"),
                 pelicula3 = new Pelicula("3", "El padrino III", 1990, "Francis Ford Coppola");
        
        String nombre = aplicacionPeliculas.insertPelicula(pelicula1);
        aplicacionPeliculas.insertPelicula(pelicula2);
        aplicacionPeliculas.insertPelicula(pelicula3);

        return "OK, datos insertados por " + nombre;
    }

    @GetMapping("/{id}")
    public Pelicula getPeliculaById(@PathVariable String id) {
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



    
}
