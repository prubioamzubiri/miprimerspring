package com.zubiri.miprimerspring.aplicacion;

import java.util.List;

import com.zubiri.miprimerspring.dominio.Pelicula;
import com.zubiri.miprimerspring.persistencia.IPersistenciaPelicula;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AplicacionPeliculas2 implements IAplicacionPeliculas{

    IPersistenciaPelicula persistenciaPelicula;

    @Override
    public Pelicula getPelicula(int id) {
        return persistenciaPelicula.getPelicula(id);
    }

    @Override
    public List<Pelicula> getPeliculas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPeliculas'");
    }

    @Override
    public String insertPelicula(Pelicula pelicula) {
        
        if(persistenciaPelicula.addPelicula(pelicula)){
            return "Pelicula insertada";
        }
        return "Error al insertar pelicula";
    }

    @Override
    public List<Pelicula> getPeliculasPorAno(int ano) {
        return persistenciaPelicula.getPeliculasPorAno(ano);
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarPelicula'");
    }


    
}
