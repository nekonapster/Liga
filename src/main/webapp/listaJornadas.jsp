<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Jornadas</title>
</head>
<body>
<h1>Lista de Jornadas</h1>
<table>
    <tr>
        <th>Número</th>
        <th>Fecha</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="jornada" items="${listaJornadas}">
        <tr>
            <td>${jornada.numero}</td>
            <td>${jornada.fecha}</td>
            <td>
                <form action="partidos" method="GET">
                    <input type="hidden" name="jornadaId" value="${jornada.id}">
                    <button type="submit">Ver partidos</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
