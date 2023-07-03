<%-- 
    Document   : testSingleton
    Created on : 30 juin 2023, 11:40:25
    Author     : koloina
--%>
<%@page import="model.Dept" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Dept d = (Dept) request.getAttribute("test"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Departement: <% out.print(d.getnom()); %></p>
        <p>Nombre employes: <% out.print(d.getnombreEmp()); %></p>
        <p>Nombre appel: <% out.print(d.getappel()); %></p>
    </body>
</html>
