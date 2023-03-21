/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu2019.framework.servlet;

import etu2019.framework.Annote;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
   import java.util.HashMap;
import etu2019.framework.Mapping;
import etu2019.framework.annotation.App;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author koloina
 */
public class FrontServlet extends HttpServlet {
     HashMap<String,Mapping> MappingUrls;
     
        @Override
    public void init() throws ServletException{
       Annote a= new Annote();
        List<Class<?>> annoted_classes = a.ClassesbyPackages();
        for (Class<?> c : annoted_classes) {
            Method[] methods = c.getMethods();
            for (Method m : methods) {
                 if (m.isAnnotationPresent(App.class)) {
                    Mapping mapping = new Mapping();
                    mapping.setclassName(c.getName());
                    mapping.setmethod(m.getName());
                    String url = m.getAnnotation(App.class).url();
                    this.MappingUrls.put(url,mapping);
                }
            }
        }
    }
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
             String query = request.getQueryString(); 
             String urlString = request.getRequestURL().toString() + "?" + query; 
             out.println("URL: " + urlString);       
             
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
