package com.zubiri.miprimerspring.aplicacion;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zubiri.miprimerspring.dominio.Actor;
import com.zubiri.miprimerspring.dominio.usuario.Usuario;
import com.zubiri.miprimerspring.dto.userdtos.UserDtoConverter;
import com.zubiri.miprimerspring.persistencia.IPersistencia;
import com.zubiri.miprimerspring.persistencia.RepositorioPelicula;

@Configuration
public class ConfiguracionAplicacion {


    @Bean
    public AplicacionUsuario getAplicacionUsuario(IPersistencia<Usuario> persistenciaUsuario,
                                                  UserDtoConverter userDtoConverter)
    {
        return new AplicacionUsuario(persistenciaUsuario, userDtoConverter);
    }

    @Bean
    public IAplicacion<Actor> getAplicacionActores(IPersistencia<Actor> persistenciaActor)
    {
        return new Aplicacion<Actor>(persistenciaActor);
    }

    @Bean
    public IAplicacionPeliculas getAplicacionPeliculas(RepositorioPelicula persistenciaPelicula)
    {
        return new AplicationPeliculas4(persistenciaPelicula);
    }


    public String getProperty(String propertyName, String defaultValue)
    {
        String propiedad = System.getProperty(propertyName);
        if(propiedad == null)
        {
            propiedad = defaultValue;
        }

        return propiedad;
    }
    
}
