package com.zubiri.miprimerspring.persistencia;

import com.zubiri.miprimerspring.dominio.Director;
import com.zubiri.miprimerspring.dominio.Pelicula;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RepositorioPelicula extends CrudRepository<Pelicula,Integer> {

    List<Pelicula> findByDirector(Director director);

    List<Pelicula> findByAnyo(int anyo);

    void deleteByAnyo(int anyo);
    
}
