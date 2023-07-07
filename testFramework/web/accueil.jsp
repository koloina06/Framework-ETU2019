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
        </br>
        <form action="upload-emp" method="post" enctype="multipart/form-data">
            <input type="file" name="fichier">
            <input type="submit" value="Upload">
        </form>
         </br>
        <form action="test-dept" method="get">
            <p>Departement: <input type="text" name="nom" ></p>
            <p>Nombre employés: <input type="text" name="nombreEmp" ></p>
            <input type="submit" value="Departement">
        </form>
          </br>
        <form action="json-emp" method="get">
            <input type="submit" value="Json">
        </form>
           </br>
        <form action="list-dept" method="get">
            <input type="submit" value="Liste des departements">
        </form>
            </br>
            <p><a href="formulaire.jsp">Formulaire</a></p>
         </br>
         <form action="delete" method="get">
            <input type="submit" value="Delete session">
        </form>
            </br>
          <form action="logout" method="get">
            <input type="submit" value="Se deconnecter">
        </form>
    </body>
</html>
