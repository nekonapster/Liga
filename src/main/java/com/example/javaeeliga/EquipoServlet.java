package com.example.javaeeliga;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.EquipoDAO;
import model.entities.Equipo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

@WebServlet("/equipo")
public class EquipoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EquipoDAO equipoDAO;

    public void init() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        equipoDAO = new EquipoDAO(sessionFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            listarEquipos(request, response);
        } else {
            switch (action) {
                case "nuevo":
                    mostrarFormularioEquipo(request, response);
                    break;
                case "insertar":
                    insertarEquipo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditarEquipo(request, response);
                    break;
                case "actualizar":
                    actualizarEquipo(request, response);
                    break;
                case "eliminar":
                    eliminarEquipo(request, response);
                    break;
                default:
                    listarEquipos(request, response);
                    break;
            }
        }
    }

    private void listarEquipos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Equipo> equipos = equipoDAO.buscarTodosLosEquipos();
        request.setAttribute("equipos", equipos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listaEquipos.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioEquipo.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        Equipo equipo = new Equipo(nombre);
        equipoDAO.guardarEquipo(equipo);
        response.sendRedirect("equipo");
    }

    private void mostrarFormularioEditarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Equipo equipoExistente = equipoDAO.buscarEquipoPorId((long) id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioEquipo.jsp");
        request.setAttribute("equipo", equipoExistente);
        dispatcher.forward(request, response);
    }

    private void actualizarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        Equipo equipo = new Equipo((long) id, nombre);
        equipoDAO.actualizarEquipo(equipo);
        response.sendRedirect("equipo");
    }

    private void eliminarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        equipoDAO.eliminarEquipo(equipoDAO.buscarEquipoPorId((long) id));
        response.sendRedirect("equipo");
    }
}

