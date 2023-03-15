package com.example.javaeeliga;
//CRUD DE EquipoServlet
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

//@WebServlet("/equipo") indica que este Servlet de Java está mapeado a la URL "/equipo". Es decir, cuando se recibe
// una solicitud HTTP con la ruta "/equipo", este Servlet será el encargado de manejar la solicitud.
@WebServlet("/equipo")
//La clase EquipoServlet es una clase que extiende HttpServlet y es responsable de manejar las solicitudes HTTP
// relacionadas con los equipos deportivos en una aplicación web.
public class EquipoServlet extends HttpServlet {

    //número de versión de la clase que se utiliza para garantizar la compatibilidad
    // durante la deserialización de objetos.
    private static final long serialVersionUID = 1L;

//   EquipoDAO que se utiliza para acceder a la base de datos y realizar operaciones CRUD en la tabla de equipos
    private EquipoDAO equipoDAO;

    //metodo que  inicializar la variable "equipoDAO", que es una instancia de la clase EquipoDAO.
    public void init() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        //Transaction tx = session.beginTransaction();

        equipoDAO = new EquipoDAO(sessionFactory);
    }
// Si el parámetro "action" no está presente en la solicitud, se llama al método listarEquipos() para
// mostrar la lista de equipos deportivos.Si el parámetro "action" está presente en la solicitud, se
// utiliza una estructura de control switch para determinar qué acción se debe realizar.
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

    //El método listarEquipos() es responsable de obtener todos los equipos deportivos de la base
    // de datos utilizando el objeto EquipoDAO
    private void listarEquipos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        List<Equipo> equipos = equipoDAO.buscarTodosLosEquipos();
        request.setAttribute("equipos", equipos);
        //El método RequestDispatcher() se utiliza para enviar la solicitud al archivo "listaEquipos.jsp" para
        // que se muestre la lista de equipos deportivos en la página web.
        RequestDispatcher dispatcher = request.getRequestDispatcher("listaEquipos.jsp");
        dispatcher.forward(request, response);
    }

    //El método mostrarFormularioEquipo() es responsable de obtener todos los equipos deportivos de la base
    // de datos
    private void mostrarFormularioEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioEquipo.jsp");
        dispatcher.forward(request, response);
    }
    //El método insertarEquipo() es responsable de obtener todos los equipos deportivos de la base
    // de datos
    private void insertarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        Equipo equipo = new Equipo(nombre);
        equipoDAO.guardarEquipo(equipo);
        response.sendRedirect("equipo");
    }
    //El método mostrarFormularioEditarEquipo() es responsable de obtener todos los equipos deportivos de la base
    // de datos
    private void mostrarFormularioEditarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Equipo equipoExistente = equipoDAO.buscarEquipoPorId((long) id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioEquipo.jsp");
        request.setAttribute("equipo", equipoExistente);
        dispatcher.forward(request, response);
    }

    //El método actualizarEquipo() es responsable de actualizar todos los equipos deportivos de la base
    // de datos
    private void actualizarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        Equipo equipo = new Equipo((long) id, nombre);
        equipoDAO.actualizarEquipo(equipo);
        response.sendRedirect("equipo");
    }
    //El método eliminarEquipo() se utiliza para eliminar un equipo deportivo existente de la base de datos.
    private void eliminarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        equipoDAO.eliminarEquipo(equipoDAO.buscarEquipoPorId((long) id));
        response.sendRedirect("equipo");
    }
}

