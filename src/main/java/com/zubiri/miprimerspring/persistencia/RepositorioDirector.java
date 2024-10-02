package com.zubiri.miprimerspring.persistencia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zubiri.miprimerspring.dominio.Director;

@Repository
public interface RepositorioDirector extends CrudRepository<Director,Integer> {

    public Director findByName(String name);
    
}
