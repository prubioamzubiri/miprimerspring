package com.zubiri.miprimerspring.persistencia;

import org.hibernate.Session;
import com.zubiri.miprimerspring.dominio.Pelicula;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PersistenciaPelicula implements IPersistenciaPelicula {
    
    private Session session;
    
    
    @Override
    public Pelicula getPelicula(String id) {
        
        Pelicula to_return =null;

        session.beginTransaction();
        to_return = (Pelicula) session.get(Pelicula.class, id);

        return to_return;
    }

    @Override
    public boolean addPelicula(Pelicula pelicula) {
        
        session.beginTransaction();
        session.save(pelicula);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<Pelicula> getPeliculas() {
        return null;
    }

    @Override
    public boolean removePelicula(String id) {
        return false;
    }

    @Override
    public List<Pelicula> getPeliculasPorAno(int ano) {
        return null;
    }
    
}
