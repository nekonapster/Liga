<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Equipo</title>
</head>
<body>
<h1>Formulario de Equipo</h1>
<form action="${action}" method="post">
    <label for="nombre">Nombre del Equipo:</label>
    <input type="text" id="nombre" name="nombre" value="${equipo.nombre}">
    <br>
    <label for="ciudad">Ciudad:</label>
    <input type="text" id="ciudad" name="ciudad" value="${equipo.ciudad}">
    <br>
    <label for="fundacion">Año de fundación:</label>
    <input type="number" id="fundacion" name="fundacion" value="${equipo.fundacion}">
    <br>
    <input type="submit" value="Guardar">
</form>
</body>
</html>
