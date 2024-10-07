package com.zubiri.miprimerspring.persistencia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zubiri.miprimerspring.dominio.Actor;

@Repository
public interface RepositorioActor extends CrudRepository<Actor, Integer>{

    public Actor findByName(String name);
    
}
