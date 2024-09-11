package com.zubiri.miprimerspring.aplicacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zubiri.miprimerspring.dominio.Pelicula;

@Configuration
public class ConfiguracionAplicacion {

    @Bean
    public IAplicacionPeliculas getAplicacionPeliculas()
    {

        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        String nombre = getProperty("nombre", "Jesus");
        IAplicacionPeliculas to_return = new AplicacionPeliculas(peliculas, nombre);
        return to_return;
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
