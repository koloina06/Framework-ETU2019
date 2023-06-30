<%-- 
    Document   : upload
    Created on : 30 juin 2023, 09:23:04
    Author     : koloina
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="etu2019.framework.FileUpload" %>
<% String file= (String) request.getAttribute("fichier") ; %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nom du fichier</h1>
        <p><% out.print(file);%></p>
    </body>
</html>
