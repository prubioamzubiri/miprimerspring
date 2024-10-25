package com.zubiri.miprimerspring.persistencia;


import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.zubiri.miprimerspring.dominio.user.Usuario;

public interface RepositorioUsuario extends CrudRepository<Usuario, String> {

    Optional<Usuario> findByUsername(String username);
    
}
