package com.zubiri.miprimerspring.aplicacion;

import java.util.List;

import com.zubiri.miprimerspring.dominio.Pelicula;

public interface IAplicacionPeliculas {

    public abstract Pelicula getPelicula(int id);

    public abstract List<Pelicula> getPeliculas();

    public abstract String insertPelicula(Pelicula pelicula);

    public List<Pelicula> getPeliculasPorAno(int ano);

    public Pelicula actualizarPelicula(Pelicula pelicula);
    
}
