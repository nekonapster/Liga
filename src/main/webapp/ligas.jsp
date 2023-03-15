<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ligas</title>
</head>
<body>
<h1>Ligas</h1>

<c:if test="${not empty listaLigas}">
    <table>
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="liga" items="${listaLigas}">
            <tr>
                <td>${liga.nombre}</td>
                <td>
                    <form method="post" action="eliminarLiga">
                        <input type="hidden" name="id" value="${liga.id}" />
                        <input type="submit" value="Eliminar" />
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<form method="post" action="crearLiga">
    <label>Nombre de la Liga:</label>
    <input type="text" name="nombre" />
    <input type="submit" value="Crear Liga" />
</form>
</body>
</html>
