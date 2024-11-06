package com.zubiri.miprimerspring.persistencia;


import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.zubiri.miprimerspring.dominio.user.Usuario;

public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
    
}
