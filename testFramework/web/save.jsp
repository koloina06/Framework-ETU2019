<%-- 
    Document   : save
    Created on : 7 juil. 2023, 16:44:28
    Author     : koloina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Emp"%>
<%@page import="java.util.HashMap"%>
<% Emp emp= (Emp) request.getAttribute("emp"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Informations:</h3>
        <p>Nom: <% out.print(emp.getnom()); %></p>
        <p>Age: <% out.print(emp.getage()); %></p>
        <p>Date: <% out.print(emp.getdate()); %></p>
        <h3>Sessions existants:</h3>
        <% for (HashMap.Entry<String, Object> entry : emp.getsession().entrySet()) { %>
        <p>Session <% out.print(entry.getKey()); %> <% out.print(entry.getValue()); %> </p>
        <% } %>
    </body>
</html>
