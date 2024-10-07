package com.zubiri.miprimerspring.aplicacion;

import com.zubiri.miprimerspring.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Aplicacion<T> implements IAplicacion<T> {

    IPersistencia<T> persistencia;

    @Override
    public boolean guardar(T t) {
        
        try{
            return persistencia.guardar(t);
        }
        catch(Exception e){
            return false;
        }
        
    }


    @Override
    public T buscar(Object id) {
        
        return persistencia.obtener(id);
    }

    @Override
    public T buscarPorNombre(String name) {
        return null;
    }

    @Override
    public boolean eliminar(Object id) {
        return false;
    }

    @Override
    public java.util.List<T> obtenerTodos() {
        return null;
    }
    
}
