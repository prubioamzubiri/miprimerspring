package com.zubiri.miprimerspring.persistencia;

import com.zubiri.miprimerspring.dominio.Pelicula;
import java.util.List;

public interface IPersistenciaPelicula {

    public Pelicula getPelicula(String id);

    public boolean addPelicula(Pelicula pelicula);

    public List<Pelicula> getPeliculas();

    public boolean removePelicula(String id);

    public List<Pelicula> getPeliculasPorAno(int ano);
    
}
