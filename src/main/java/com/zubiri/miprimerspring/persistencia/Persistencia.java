package com.zubiri.miprimerspring.persistencia;

import java.util.List;

import org.hibernate.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Persistencia<T> implements IPersistencia<T>{

    private Session session;
    private Class<T> classType;

    @Override
    public boolean guardar(T t) {
        try{
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
            return true;
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public T obtener(Object id) {
        
        return session.get(classType, id);
    }

    @Override
    public boolean actualizar(T t) {
        
        try{
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
            return true;
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean eliminar(T t) {
            
            try{
                session.beginTransaction();
                session.remove(t);
                session.getTransaction().commit();
            }
            catch(Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
    
                return false;
            }

            return true;
    }

    @Override
    public List<T> obtenerTodos() {
        
        return session.createQuery("from " + classType.getName(),classType).list();
    }

    @Override
    public List<T> obtenerPorNombre(String nombre) {
        
        return session
                .createQuery("from " + classType.getName() + " where nombre = :nombre",classType)
                .setParameter("nombre", nombre).list();
    }

    @Override
    public List<T> query(String key, String value) {
            
            return session
                    .createQuery("from " + classType.getName() + " where " + key + " = :value",classType)
                    .setParameter("value", value).list();
    }
    
}
