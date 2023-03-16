package com.example.javaeeliga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.EquipoDAO;
import model.DAO.JornadaDAO;
import model.DAO.PartidoDAO;
import model.entities.Equipo;
import model.entities.Jornada;
import model.entities.Partido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/partido")
public class PartidoServlet extends HttpServlet {
    private PartidoDAO partidoDAO;
    private EquipoDAO equipoDAO;

    private JornadaDAO jornadaDAO;

    public void init() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        jornadaDAO = new JornadaDAO(sessionFactory);
        equipoDAO = new EquipoDAO(sessionFactory);
        partidoDAO = new PartidoDAO(sessionFactory);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Partido> partidos = partidoDAO.buscarTodosLosPartidos();
        request.setAttribute("partidos", partidos);
        request.getRequestDispatcher("partidos.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int equipoLocalId = Integer.parseInt(request.getParameter("equipoLocal"));
        int equipoVisitanteId = Integer.parseInt(request.getParameter("equipoVisitante"));
        int jornadaId = Integer.parseInt(request.getParameter("jornada"));
        int puntosLocal = Integer.parseInt(request.getParameter("puntosLocal"));
        int puntosVisitante = Integer.parseInt(request.getParameter("puntosVisitante"));

        Equipo equipoLocal = equipoDAO.buscarEquipoPorId( (long) equipoLocalId);
        Equipo equipoVisitante = equipoDAO.buscarEquipoPorId((long) equipoVisitanteId);
        Jornada jornada = jornadaDAO.obtenerJornada(jornadaId);

        Partido partido = new Partido();
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setJornada(jornada);
        partido.setPuntosLocal(puntosLocal);
        partido.setPuntosVisitante(puntosVisitante);

        partidoDAO.guardarPartido(partido);

        response.sendRedirect("partido");
    }

}

