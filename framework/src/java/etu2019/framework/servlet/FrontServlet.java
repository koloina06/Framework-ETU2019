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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

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
    
    public void setAttribute(HttpServletRequest request,String[] attribute, Field[] att,Object o){
        try{
            for(int i=0; i<att.length; i++){
                for(int j=0; j<attribute.length; j++){
                    if(att[i].getName().equalsIgnoreCase(attribute[j])){
                        if(att[i].getType()==String.class) 
                        {
                            Method m1= o.getClass().getMethod("set" + att[i].getName(), String.class);
                            m1.invoke(o, request.getParameter(att[i].getName()));
                        }
                        if(att[i].getType()==int.class) 
                        {
                            Method m1= o.getClass().getMethod("set" + att[i].getName(), int.class );
                            m1.invoke(o, Integer.parseInt(request.getParameter(att[i].getName())));
                        }
                        if(att[i].getType()==double.class) 
                        {
                            Method m1= o.getClass().getMethod("set" + att[i].getName(), double.class );
                            m1.invoke(o, Double.parseDouble(request.getParameter(att[i].getName())));
                        }
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
            
             String [] tab= urlString.split("/");
             String url= tab[tab.length-1];
              if(this.mappingUrls.containsKey(url)){
                 String className= this.mappingUrls.get(url).getclassName();
                 String method= this.mappingUrls.get(url).getmethod();
                 Class<?> c= Class.forName(className);
                 Method m= c.getDeclaredMethod(method);
                 Object o= c.newInstance();
                      if(request.getParameterMap()!=null){
                        Map<String, String[]> parameter= request.getParameterMap();
                        Set<String> parameterName= parameter.keySet();
                        String[] attribute= parameterName.toArray(new String[parameterName.size()]);
                        Field[] att= o.getClass().getDeclaredFields();
                        this.setAttribute(request,attribute,att,o);
                      }
                      
                 Object object=  m.invoke(o);
                    if(object != null){
                        if(object.getClass() == ModelView.class)
                      {
                            ModelView mv= (ModelView) object;
                            RequestDispatcher dispat = request.getRequestDispatcher(mv.getView());
                            HashMap<String,Object> data= mv.getData();
                            for(HashMap.Entry<String,Object> d : data.entrySet()){
                                request.setAttribute(d.getKey(),d.getValue());
                            }
                            dispat.forward(request,response);
                      }
                    }
                      
              }
        }catch(Exception e){
            e.printStackTrace(out);
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