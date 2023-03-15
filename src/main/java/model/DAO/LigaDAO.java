package model.DAO;

import model.entities.Liga;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LigaDAO {

    private SessionFactory sessionFactory;

    public LigaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Liga guardarLiga(Liga liga) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(liga);
            tx.commit();
            return liga;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Liga buscarLigaPorId(Long id) {
        Session session = sessionFactory.openSession();
        try {
            Liga liga = session.get(Liga.class, id);
            return liga;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Liga> buscarTodasLasLigas() {
        Session session = sessionFactory.openSession();
        try {
            List<Liga> ligas = session.createQuery("from Liga", Liga.class).list();
            return ligas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void actualizarLiga(Liga liga) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(liga);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void eliminarLiga(Liga liga) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(liga);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

