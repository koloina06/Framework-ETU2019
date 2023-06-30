<%-- 
    Document   : index
    Created on : 3 mars 2023, 10:02:43
    Author     : koloina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="emp-all" method="get">
            <input type="submit" value="Voir la liste des employés">
        </form>
        <form action="upload-emp" method="post" enctype="multipart/form-data">
            <input type="file" name="fichier">
            <input type="submit" value="Upload">
        </form>
        <a href="formulaire.jsp">Formulaire</a>
    </body>
</html>
