package model.DAO;
//uso biblioteca HIBERNATE para mapear los datos y asi evitar hacer consultas SQL.

//define una clase Java llamada EquipoDAO que se utiliza para acceder a la base de datos y realizar operaciones CRUD
// (crear, leer, actualizar y eliminar) en la tabla "Equipo".
import model.entities.Equipo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EquipoDAO {

    private SessionFactory sessionFactory;

    public EquipoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Equipo guardarEquipo(Equipo equipo) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(equipo);
            tx.commit();
            return equipo;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Equipo buscarEquipoPorId(Long id) {
        Session session = sessionFactory.openSession();
        try {
            Equipo equipo = session.get(Equipo.class, id);
            return equipo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Equipo> buscarTodosLosEquipos() {
        Session session = sessionFactory.openSession();
        try {
            List<Equipo> equipos = session.createQuery("from Equipo", Equipo.class).list();
            return equipos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void actualizarEquipo(Equipo equipo) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(equipo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void eliminarEquipo(Equipo equipo) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(equipo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
