<%-- 
    Document   : fiche
    Created on : 2 mai 2023, 15:39:49
    Author     : koloina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Emp"%>
<% String nom= (String) request.getAttribute("fiche");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p><% out.print(nom);%></p>
    </body>
</html>
