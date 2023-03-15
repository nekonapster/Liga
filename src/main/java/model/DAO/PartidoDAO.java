package model.DAO;

import model.entities.Partido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PartidoDAO {

    private SessionFactory sessionFactory;

    public PartidoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Partido guardarPartido(Partido partido) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(partido);
            tx.commit();
            return partido;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Partido buscarPartidoPorId(Long id) {
        Session session = sessionFactory.openSession();
        try {
            Partido partido = session.get(Partido.class, id);
            return partido;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Partido> buscarTodosLosPartidos() {
        Session session = sessionFactory.openSession();
        try {
            List<Partido> partidos = session.createQuery("from Partido", Partido.class).list();
            return partidos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void actualizarPartido(Partido partido) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(partido);
            tx.commit();
        } catch (Exception e) {
            //if (tx
        } finally {
            session.close();
        }
    }

    public void eliminarPartido(Partido partido) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(partido);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
