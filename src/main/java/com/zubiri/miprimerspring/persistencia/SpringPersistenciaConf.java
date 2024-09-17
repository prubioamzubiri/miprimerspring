package com.zubiri.miprimerspring.persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

import com.zubiri.miprimerspring.dominio.Pelicula;

@org.springframework.context.annotation.Configuration
public class SpringPersistenciaConf {

    @Bean
    public IPersistenciaPelicula getPersistenciaPelicula()
    {
        return new PersistenciaPelicula(getSession());
    }

    public Session getSession()
    {
        Configuration conf = new Configuration();

        SessionFactory factory = conf.configure().addAnnotatedClass(Pelicula.class)
                                                 .buildSessionFactory();

        Session session = factory.openSession();

        return session;
    }
    
}
