package com.zubiri.miprimerspring.aplicacion;

import java.util.List;

import com.zubiri.miprimerspring.dominio.Pelicula;

public interface IAplicacionPeliculas {

    public abstract Pelicula getPelicula(String id);

    public abstract List<Pelicula> getPeliculas();

    public abstract void insertPelicula(Pelicula pelicula);
    
}
