package com.zubiri.miprimerspring.aplicacion;

import java.util.List;

import com.zubiri.miprimerspring.dominio.Pelicula;
import com.zubiri.miprimerspring.persistencia.IPersistencia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AplicacionPeliculas3 implements IAplicacionPeliculas{

    IPersistencia<Pelicula> persistenciaPelicula;

    @Override
    public Pelicula getPelicula(int id) {
        return persistenciaPelicula.obtener(id);
    }

    @Override
    public List<Pelicula> getPeliculas() {
        
        return persistenciaPelicula.obtenerTodos();
    }

    @Override
    public String insertPelicula(Pelicula pelicula) {
        
        if(persistenciaPelicula.guardar(pelicula)){
            return "Pelicula insertada";
        }
        return "Error al insertar pelicula";
    }

    @Override
    public List<Pelicula> getPeliculasPorAno(int ano) {
        return persistenciaPelicula.query("anyo", String.valueOf(ano));
    }


    
}
