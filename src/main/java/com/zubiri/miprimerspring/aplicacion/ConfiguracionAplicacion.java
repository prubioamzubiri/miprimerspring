package com.zubiri.miprimerspring.aplicacion;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zubiri.miprimerspring.dominio.Actor;
import com.zubiri.miprimerspring.dto.converter.UserDtoConverter;
import com.zubiri.miprimerspring.persistencia.IPersistencia;
import com.zubiri.miprimerspring.persistencia.RepositorioPelicula;
import com.zubiri.miprimerspring.persistencia.RepositorioUsuario;

@Configuration
public class ConfiguracionAplicacion {

    @Bean
    public AplicacionUsuario getAplicacionUsuario(RepositorioUsuario repositorioUsuario, UserDtoConverter userConverter, PasswordEncoder passwordEncoder)
    {
        return new AplicacionUsuario(repositorioUsuario, userConverter, passwordEncoder);
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
