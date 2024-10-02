package com.zubiri.miprimerspring.persistencia;

import java.util.List;

import com.zubiri.miprimerspring.dominio.Evento;
import com.zubiri.miprimerspring.dominio.Premio;
import com.zubiri.miprimerspring.dominio.PremioId;

public interface IPersistenciaPremios {

    public abstract Premio getPremio(PremioId premioId);

    public abstract boolean addPremio(Premio premio);

    public abstract Evento getEvento(String nombreEvento);

    public abstract boolean addEvento(Evento evento);

    public abstract List<Premio> getPremios();
    
}
