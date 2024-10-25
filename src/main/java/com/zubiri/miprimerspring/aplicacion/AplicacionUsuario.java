package com.zubiri.miprimerspring.aplicacion;

import com.zubiri.miprimerspring.dominio.user.Usuario;
import com.zubiri.miprimerspring.persistencia.RepositorioUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AplicacionUsuario {

     RepositorioUsuario repositorioUsuario;

    public Usuario guardar(Usuario usuario) {
        
        try{

            return repositorioUsuario.save(usuario);
        }
        catch(Exception e){
            return null;
        }
    }
}