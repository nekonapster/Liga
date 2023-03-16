<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Equipos</title>
</head>
<body>
<h1>Equipos</h1>
<table table="1px">
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="equipo" items="${listaEquipos}">
        <tr>
            <td>${equipo.nombre}</td>
            <td>
                <a href="editarEquipo?id=${equipo.id}">Editar</a>
                <a href="eliminarEquipo?id=${equipo.id}">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="nuevoEquipo">Agregar Equipo</a>
</body>
</html>
