package com.zubiri.miprimerspring.persistencia;

import java.util.List;

import org.hibernate.Session;

import com.zubiri.miprimerspring.dominio.Evento;
import com.zubiri.miprimerspring.dominio.Premio;
import com.zubiri.miprimerspring.dominio.PremioId;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersistenciaPremios implements IPersistenciaPremios{

    private Session session;

    @Override
    public Premio getPremio(PremioId premioId) {

        session.beginTransaction();

        Premio to_return = session.get(Premio.class, premioId);

        session.getTransaction().commit();

        return to_return;
    }
       

    @Override
    public boolean addPremio(Premio premio) {
        
        try{
            session.beginTransaction();
            session.persist(premio);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Evento getEvento(String nombreEvento) {

        session.beginTransaction();

        Evento to_return = session.get(Evento.class, nombreEvento);

        session.getTransaction().commit();

        return to_return;
    }
        

    @Override
    public boolean addEvento(Evento evento) {
            
            try{
                session.beginTransaction();
                session.persist(evento);
                session.getTransaction().commit();
                return true;
            }catch(Exception e){
                session.getTransaction().rollback();
                return false;
            }
    }

    @Override
    public List<Premio> getPremios() {
        
        session.beginTransaction();

        List<Premio> to_return = session.createQuery("from Premio", Premio.class).list();

        session.getTransaction().commit();

        return to_return;
    }
    
}
