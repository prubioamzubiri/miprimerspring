package com.zubiri.miprimerspring.persistencia;

import org.springframework.data.repository.CrudRepository;

import com.zubiri.miprimerspring.dominio.Dibujo;

public interface RepositorioDibujo extends CrudRepository<Dibujo, Long> {
    
}
