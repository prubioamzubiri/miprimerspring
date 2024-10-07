package com.zubiri.miprimerspring.persistencia;

import org.springframework.data.repository.CrudRepository;

import com.zubiri.miprimerspring.dominio.Actor;

public interface RepositorioActor extends CrudRepository<Actor, Integer>{

    public Actor findByName(String name);
    
}
