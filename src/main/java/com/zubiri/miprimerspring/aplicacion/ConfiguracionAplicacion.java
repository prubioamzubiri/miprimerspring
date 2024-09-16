package com.zubiri.miprimerspring.aplicacion;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zubiri.miprimerspring.persistencia.IPersistenciaPelicula;

@Configuration
public class ConfiguracionAplicacion {

    @Bean
    public IAplicacionPeliculas getAplicacionPeliculas(IPersistenciaPelicula persistenciaPelicula)
    {
        return new AplicacionPeliculas2(persistenciaPelicula);
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
