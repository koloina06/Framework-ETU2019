<%-- 
    Document   : formulaire
    Created on : 25 avr. 2023, 11:23:02
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
        <form action="save-emp" method="get">
            <p>Nom: <input type="text" name="nom"></p>
            <p>Age: <input type="text" name="age"></p>
            <p>Date: <input type="date" name="date"></p>
            <input type="submit" value="valider">
        </form>
    </body>
</html>
