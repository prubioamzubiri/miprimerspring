package com.zubiri.miprimerspring.aplicacion;

import java.util.List;

import com.zubiri.miprimerspring.dominio.Pelicula;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AplicacionPeliculas implements IAplicacionPeliculas{

    private List<Pelicula> peliculas;
    private String nombre;


    @Override
    public Pelicula getPelicula(int id) {
        
        boolean encontrado = false;
        Pelicula to_return = null;
        int index = 0,
            length = peliculas.size();

        while((!encontrado)&&(index < length))
        {
            if(peliculas.get(index).getId()==id){
                to_return = peliculas.get(index);
                encontrado = true;                
            }
            index++;
        }

        return to_return;

    }

    @Override
    public List<Pelicula> getPeliculas() {
        return this.peliculas;
    }

    @Override
    public String insertPelicula(Pelicula pelicula) {
        this.peliculas.add(pelicula);
        return nombre;
    }

    @Override
    public List<Pelicula> getPeliculasPorAno(int ano) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPeliculasPorAno'");
    }

    
    
}
