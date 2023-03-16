package com.example.javaeeliga;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.JornadaDAO;
import model.DAO.LigaDAO;
import model.entities.Jornada;
import model.entities.Liga;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/liga")
public class LigaServlet extends HttpServlet {

    private LigaDAO ligaDAO;

    public void init() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        ligaDAO = new LigaDAO(sessionFactory);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Liga> ligas = ligaDAO.buscarTodasLasLigas();
        request.setAttribute("ligas", ligas);
        request.getRequestDispatcher("ligas.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        Liga liga = new Liga();
        liga.setNombre(nombre);
        ligaDAO.guardarLiga(liga);
        response.sendRedirect("liga");
    }

}



