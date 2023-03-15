package com.example.javaeeliga;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.JornadaDAO;
import model.entities.Jornada;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/jornada")
public class JornadaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private JornadaDAO jornadaDAO;

    public void init() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        jornadaDAO = new JornadaDAO(sessionFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listarJornadas(request, response);
                break;
            case "formNuevo":
                mostrarFormNuevo(request, response);
                break;
            case "formEditar":
                mostrarFormEditar(request, response);
                break;
            case "guardar":
                guardarJornada(request, response);
                break;
            case "actualizar":
                actualizarJornada(request, response);
                break;
            case "eliminar":
                eliminarJornada(request, response);
                break;
            default:
                listarJornadas(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listarJornadas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Jornada> listaJornadas = jornadaDAO.obtenerTodasLasJornadas();

        request.setAttribute("listaJornadas", listaJornadas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listaJornadas.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioJornada.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Jornada jornadaExistente = jornadaDAO.obtenerJornada(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioJornada.jsp");
        request.setAttribute("jornada", jornadaExistente);
        dispatcher.forward(request, response);
    }

    private void guardarJornada(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numero = Integer.parseInt(request.getParameter("numero"));
        String fecha = request.getParameter("fecha");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        Jornada nuevaJornada = new Jornada(numero, LocalDate.parse(fecha, formatter));
        jornadaDAO.crearJornada(nuevaJornada);

        response.sendRedirect("jornada");
    }

    private void actualizarJornada(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int numero = Integer.parseInt(request.getParameter("numero"));
        String fecha = request.getParameter("fecha");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        Jornada jornadaExistente = jornadaDAO.obtenerJornada(id);
        jornadaExistente.setNumero(numero);
        jornadaExistente.setFecha(LocalDate.parse(fecha, formatter));

        jornadaDAO.actualizarJornada(jornadaExistente);

        response.sendRedirect("jornada");
    }

    private void eliminarJornada(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Jornada jornadaExistente = jornadaDAO.obtenerJornada(id);
        jornadaDAO.eliminarJornada(jornadaExistente);

        response.sendRedirect("jornada");
    }
}
