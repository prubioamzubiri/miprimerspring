package com.zubiri.miprimerspring.persistencia;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zubiri.miprimerspring.dominio.Actor;
import com.zubiri.miprimerspring.dominio.Director;
import com.zubiri.miprimerspring.dominio.Evento;
import com.zubiri.miprimerspring.dominio.Pelicula;
import com.zubiri.miprimerspring.dominio.Personal;
import com.zubiri.miprimerspring.dominio.Premio;
import com.zubiri.miprimerspring.dominio.PremioId;
import com.zubiri.miprimerspring.dominio.PremioPelicula;

import jakarta.persistence.EntityManagerFactory;

@EnableJpaRepositories(basePackages = "com.zubiri.miprimerspring.persistencia")
@org.springframework.context.annotation.Configuration
public class SpringPersistenciaConf {

    //JPA Configuration
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/Peliculas");
        dataSource.setUsername("Pablo");
        dataSource.setPassword("123456");
        return dataSource;
    }

    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        //factoryBean.setPersistenceUnitName("Peliculas");
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("com.zubiri.miprimerspring.dominio");
        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory eM) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(eM);

        return transactionManager;
    }

    //Interfaces Bean
    @Bean
    public IPersistencia<Pelicula> getPersistenciaPelicula(){
        
        return new Persistencia<Pelicula>(getSession(), Pelicula.class); 
    }

    @Bean
    public IPersistencia<Actor> getPersistenciaActor(){           
            return new Persistencia<Actor>(getSession(), Actor.class); 
    }

    @Bean
    public IPersistenciaPremios getPersistenciaPremios()
    {
        return new PersistenciaPremios(getSession());
    }

    @Bean
    public IPersistenciaPelicula getPersistenciaPelicula2()
    {
        return new PersistenciaPelicula(getSession());
    }

    //Hibernate Session
    public Session getSession()
    {
        /* StandardServiceRegistry registry =
				new StandardServiceRegistryBuilder()
                        .configure()
						.build();

        SessionFactory sessionFactory = new MetadataSources(registry)
							.addAnnotatedClass(Pelicula.class)
							.buildMetadata()
							.buildSessionFactory(); */

        Configuration conf = new Configuration();

        SessionFactory sessionFactory = conf.configure().addAnnotatedClass(Evento.class)
                                                        .addAnnotatedClass(PremioId.class)
                                                        .addAnnotatedClass(Premio.class)
                                                        .addAnnotatedClass(Personal.class)
                                                        .addAnnotatedClass(Director.class)
                                                        .addAnnotatedClass(Actor.class)
                                                        .addAnnotatedClass(Pelicula.class)
                                                        .addAnnotatedClass(PremioPelicula.class)
                                                        .buildSessionFactory();

        Session session = sessionFactory.openSession();

        return session;
    }
    
}
