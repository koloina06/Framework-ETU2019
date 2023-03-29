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
import etu2019.framework.ModelView;
import etu2019.framework.annotation.App;
import etu2019.framework.annotation.ControllerA;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author koloina
 */
public class FrontServlet extends HttpServlet {
     HashMap<String,Mapping> mappingUrls= new HashMap<String, Mapping>();

     
        @Override
    public void init() throws ServletException{
            ServletContext context= getServletContext();
            String p= context.getInitParameter("package");
            try{
                 List<Class<?>> annoted_classes =  Annote.getClassesWithAnnotation2(ControllerA.class,p);
             for (Class<?> c : annoted_classes) {
                
            Method[] methods = c.getMethods();
            for (Method m : methods) {
                 if (m.isAnnotationPresent(App.class)) {
                    Mapping mapping = new Mapping();
                    mapping.setclassName(c.getName());
                    mapping.setmethod(m.getName());
                    App app = m.getAnnotation(App.class);
                    String url = app.url();
                    this.mappingUrls.put(url,mapping);
                }
            }
            }
            }catch(Exception e){
            
            }
            
        }
    
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
        try  {  
             String query = request.getQueryString();
             String urlString = request.getRequestURL().toString(); 
             //out.print(urlString);
             String [] tab= urlString.split("/");
             String url= tab[tab.length-1];
             if(this.mappingUrls.containsKey(url)){
                 String className= this.mappingUrls.get(url).getclassName();
                 String method= this.mappingUrls.get(url).getmethod();
                 Class<?> c= Class.forName(className);
                 Method m= c.getDeclaredMethod(method);
                 Object o= c.newInstance();
                 ModelView mv= (ModelView) m.invoke(o);
                 if(mv.getClass() == ModelView.class){
                     RequestDispatcher dispat = request.getRequestDispatcher(mv.getView());
                     dispat.forward(request,response);
                     
                 }
             }
        }catch(Exception e){
            //e.printStackTrace(out);
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
