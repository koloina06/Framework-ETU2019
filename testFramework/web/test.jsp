<%-- 
    Document   : test
    Created on : 24 mars 2023, 10:21:18
    Author     : koloina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Emp"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Emp> list= (ArrayList<Emp>) request.getAttribute("listEmp");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h4>Liste des employes</h4>
        <% for(int i=0; i<list.size(); i++){ %>
            <p><a href="fiche-emp?nom=<% out.print(list.get(i).getnom()); %>"> <% out.print(list.get(i).getnom()); %> <% out.print(list.get(i).getappel()); %></a></p>
        <% } %>      
    </body>
</html>
