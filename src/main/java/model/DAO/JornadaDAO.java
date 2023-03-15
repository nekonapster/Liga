package model.DAO;

import java.util.List;

import model.entities.Jornada;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class JornadaDAO {
    private SessionFactory sessionFactory;

    public JornadaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearJornada(Jornada jornada) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(jornada);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Jornada obtenerJornada(int id) {
        Session session = sessionFactory.openSession();
        Jornada jornada = null;
        try {
            jornada = session.get(Jornada.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return jornada;
    }

    public void actualizarJornada(Jornada jornada) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(jornada);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void eliminarJornada(Jornada jornada) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(jornada);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Jornada> obtenerTodasLasJornadas() {
        Session session = sessionFactory.openSession();
        List<Jornada> jornadas = null;
        try {
            Query<Jornada> query = session.createQuery("FROM Jornada", Jornada.class);
            jornadas = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return jornadas;
    }
}
