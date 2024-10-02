package com.zubiri.miprimerspring.persistencia;

import org.hibernate.Session;

import com.zubiri.miprimerspring.dominio.Actor;
import com.zubiri.miprimerspring.dominio.Director;
import com.zubiri.miprimerspring.dominio.Pelicula;


import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PersistenciaPelicula implements IPersistenciaPelicula {
    
    private Session session;
    
    
    @Override
    public Pelicula getPelicula(int id) {
        
        Pelicula to_return =null;

        session.beginTransaction();
        to_return = (Pelicula) session.get(Pelicula.class, id);
        session.getTransaction().commit();

        return to_return;
    }

    @Override
    public boolean addPelicula(Pelicula pelicula) {

        
        
        try{


            for(Actor a : pelicula.getActores())
            {
                session.beginTransaction();
                session.persist(a);
                session.getTransaction().commit();
            }

            if(!guardarDirector(pelicula.getDirector())){
                pelicula.setDirector(getDirectorByNombre(pelicula.getDirector().getName()));
            }

        
            session.beginTransaction();

            Pelicula pelicual_buscada = session.createQuery("from Pelicula where name = :titulo", Pelicula.class).setParameter("titulo", pelicula.getName()).uniqueResult();

            if(pelicual_buscada != null ){
                
                session.getTransaction().rollback();
                return false;
            }

            session.persist(pelicula);
            session.getTransaction().commit();
            return true;
        }
    catch(Exception e){
        session.getTransaction().rollback();
        return false;
    }

    }

    @Override
    public List<Pelicula> getPeliculas() {
        return null;
    }

    @Override
    public boolean removePelicula(String id) {
        return false;
    }

    @Override
    public List<Pelicula> getPeliculasPorAno(int ano) {

        List<Pelicula> to_return = null;
        
        try{

            session.beginTransaction();

            to_return = session.createQuery("from Pelicula where anyo = :ano", Pelicula.class)
                                .setParameter("ano", ano).list();
            session.getTransaction().commit();

            return to_return;

        }
        catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
        
    }

    private boolean guardarDirector(Director director)
    {
        try{
            session.beginTransaction();
            session.persist(director);
        }
        catch(Exception e){
            session.getTransaction().rollback();            
            return false;
        }
        session.getTransaction().commit();
        return true;
    }
    
    private Director getDirectorByNombre(String nombre)
    {
        Director to_return = null;
        try{
            session.beginTransaction();
            to_return = session.createQuery("from Director where name = :nombre", Director.class)
            .setParameter("nombre", nombre).uniqueResult();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
        session.getTransaction().commit();
        return to_return;
    }
}
