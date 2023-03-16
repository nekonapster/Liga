<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario Jornada</title>
</head>
<body>
<h1>Formulario Jornada</h1>
<form action="guardarJornada" method="post">
    <label for="numero">Número de jornada:</label>
    <input type="number" id="numero" name="numero" required>
    <br>
    <label for="fecha">Fecha:</label>
    <input type="date" id="fecha" name="fecha" required>
    <br>
    <label for="idLiga">Liga:</label>
    <select id="idLiga" name="idLiga">
        <c:forEach var="liga" items="${listaLigas}">
            <option value="${liga.id}">${liga.nombre}</option>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="Guardar">
</form>
<br>
<a href="index.jsp">Volver</a>
</body>
</html>
