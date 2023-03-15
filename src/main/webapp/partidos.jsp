<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Partidos</title>
</head>
<body>
<h1>Lista de Partidos</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Equipo Local</th>
        <th>Equipo Visitante</th>
        <th>Fecha</th>
        <th>Resultado</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="partido" items="${listaPartidos}">
        <tr>
            <td>${partido.id}</td>
            <td>${partido.equipoLocal.nombre}</td>
            <td>${partido.equipoVisitante.nombre}</td>
            <td>${partido.fecha}</td>
            <td>${partido.resultadoLocal} - ${partido.resultadoVisitante}</td>
            <td>
                <a href="editarPartido?id=${partido.id}">Editar</a>
                <a href="eliminarPartido?id=${partido.id}">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="nuevoPartido">Agregar Nuevo Partido</a>
</body>
</html>
