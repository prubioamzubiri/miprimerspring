package com.zubiri.miprimerspring.aplicacion;

import java.util.List;

import com.zubiri.miprimerspring.dominio.Actor;

public interface IAplicacionActor {

    public Actor guardarActor(Actor actor);

    public Actor buscarActor(int id);

    public Actor buscarActor(String name);

    public boolean eliminarActor(int id);

    public List<Actor> obtenerActores();
    
}
