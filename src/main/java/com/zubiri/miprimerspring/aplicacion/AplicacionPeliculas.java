package com.zubiri.miprimerspring.aplicacion;

import java.util.List;

import com.zubiri.miprimerspring.dominio.Pelicula;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AplicacionPeliculas implements IAplicacionPeliculas{

    private List<Pelicula> peliculas;


    @Override
    public Pelicula getPelicula(String id) {
        
        boolean encontrado = false;
        Pelicula to_return = null;
        int index = 0,
            length = peliculas.size();

        while((!encontrado)&&(index < length))
        {
            if(peliculas.get(index).getId().compareTo(id)==0){
                to_return = peliculas.get(index);
                encontrado = true;                
            }
        }

        return to_return;

    }

    @Override
    public List<Pelicula> getPeliculas() {
        return this.peliculas;
    }

    @Override
    public void insertPelicula(Pelicula pelicula) {
        this.peliculas.add(pelicula);
    }
    
}
