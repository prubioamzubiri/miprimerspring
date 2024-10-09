package com.zubiri.miprimerspring.aplicacion;

import java.util.List;


import com.zubiri.miprimerspring.dominio.Pelicula;
import com.zubiri.miprimerspring.persistencia.RepositorioPelicula;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AplicationPeliculas4 implements IAplicacionPeliculas{

    RepositorioPelicula repositorioPelicula;

    @Override
    public Pelicula getPelicula(int id) {
        
        Pelicula pelicula;

        pelicula = repositorioPelicula.findById(id).get();
        
        return pelicula;
    }

    @Override
    public List<Pelicula> getPeliculas() {
       return (List<Pelicula>) repositorioPelicula.findAll(); 
    }

    @Override
    public String insertPelicula(Pelicula pelicula) {
        
        repositorioPelicula.save(pelicula);
        return "Pelicula insertada";
    }

    @Override
    public List<Pelicula> getPeliculasPorAno(int ano) {
        
        return repositorioPelicula.findByAnyo(ano);
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) {
        
        return repositorioPelicula.save(pelicula);
    }

    

}
    

