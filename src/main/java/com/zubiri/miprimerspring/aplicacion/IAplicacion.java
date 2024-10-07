package com.zubiri.miprimerspring.aplicacion;

import java.util.List;

import com.zubiri.miprimerspring.dominio.Actor;

public interface IAplicacion<T> {

    public T guardar(T t);

    public T buscar(Object id);

    public T buscarPorNombre(String name);

    public boolean eliminar(Object id);

    public List<T> obtenerTodos();
    
}
