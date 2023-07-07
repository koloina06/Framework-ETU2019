/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu2019.framework.servlet;

import com.google.gson.Gson;
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
import etu2019.framework.FileUpload;
import etu2019.framework.annotation.Auth;
import etu2019.framework.annotation.RestAPI;
import etu2019.framework.annotation.Scope;
import etu2019.framework.annotation.Session;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author koloina
 */

@MultipartConfig
public class FrontServlet extends HttpServlet {
     HashMap<String,Mapping> mappingUrls= new HashMap<String, Mapping>();
     HashMap<Class,Object> singleton= new HashMap<Class,Object>();
     Gson gson = new Gson();
     boolean containsFile= false;
     String sessionName;
     String sessionProfil;
     String sessionAttribute;
     
    @Override
    public void init() throws ServletException{
            ServletContext context= getServletContext();
            String p= context.getInitParameter("package");
            sessionName = context.getInitParameter("sessionName");
            System.out.println("sessionName init "+ sessionName);
            sessionProfil = context.getInitParameter("sessionProfil");
            System.out.println("sessionProfil init "+ sessionProfil);
            sessionAttribute = context.getInitParameter("sessionAttribute");
            try{
                 List<Class<?>> annoted_classes =  Annote.getClassesWithAnnotation2(ControllerA.class,p);
                 for (Class<?> c : annoted_classes) { 
                    if(c.isAnnotationPresent(Scope.class)){
                        Scope scope= c.getAnnotation(Scope.class);
                        if(scope.valeur().equals("singleton")){
                           Object obj= c.newInstance();
                           this.singleton.put(c,obj);
                        }
                    }
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
                        Method m1= o.getClass().getMethod("set" + att[i].getName(), att[i].getType());
                        if(att[i].getType()==String.class) m1.invoke(o, request.getParameter(att[i].getName()));
                        if(att[i].getType()==int.class)  m1.invoke(o, Integer.parseInt(request.getParameter(att[i].getName())));
                        if(att[i].getType()==double.class)  m1.invoke(o, Double.parseDouble(request.getParameter(att[i].getName())));
                        if(att[i].getType()==Date.class)  m1.invoke(o, Date.valueOf(request.getParameter(att[i].getName())));
                    }
                }                           
            }
        }catch(Exception e){
        
        } 
    }
    
    public void reset(HttpServletRequest request, Field[] att, Object o){
        try{
            for(int i=0; i<att.length; i++){
                Method m= o.getClass().getMethod("set" + att[i].getName(), att[i].getType());
                if(att[i].getType()==String.class) m.invoke(o, null);
                if(att[i].getType()==int.class || att[i].getType()==double.class)  m.invoke(o,0);
                if(att[i].getType()==Date.class)  m.invoke(o, null);
                if(att[i].getType()==Boolean.class)  m.invoke(o, "false");
            }
        }catch(Exception e){
        
        }
    }
    
     public Object cast(HttpServletRequest request, Parameter parametre, Object o) {
        try {
            Method method = o.getClass().getMethod("get" + parametre.getName());
            return method.invoke(o);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
     
     private FileUpload fileTraitement( Collection<Part> files, Field field){
        FileUpload file = new FileUpload();
        String name = field.getName();
        boolean exists = false;
        String filename = null;
        Part filepart = null;
        for( Part part : files ){
            if( part.getName().equals(name) ){
                filepart = part;
                exists = true;
                break;
            }
        }
        try(InputStream io = filepart.getInputStream()){
            ByteArrayOutputStream buffers = new ByteArrayOutputStream();
            byte[] buffer = new byte[(int)filepart.getSize()];
            int read;
            while( ( read = io.read( buffer , 0 , buffer.length )) != -1 ){
                buffers.write( buffer , 0, read );
            }
            file.setname( this.getFileName(filepart) );
            file.setbytes( buffers.toByteArray() );
            return file;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] parts = contentDisposition.split(";");
        for (String partStr : parts) {
            if (partStr.trim().startsWith("filename"))
                return partStr.substring(partStr.indexOf('=') + 1).trim().replace("\"", "");
        }
        return null;
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
                 Method[] methods = c.getDeclaredMethods();
                 Method m = null;
                for (Method methode : methods) {
                    if(methode.getName() == method){
                        m = methode;
                    }
                }
                Object o= null;
                
                if (m.isAnnotationPresent(Auth.class)) {
                    Auth a = (Auth) m.getAnnotation(Auth.class);
                    if (request.getSession().getAttribute(sessionName) != null) {
                        System.out.println(request.getSession().getAttribute(sessionName));
                        if ((a.profil().isEmpty() == false
                                && !a.profil().equals(request.getSession().getAttribute(sessionProfil)))) {
                            throw new Exception("privilege non accordé");
                        }
                    } else {
                        throw new Exception("pas de session");
                    }
                }
                
                if(this.singleton.containsKey(c)){
                    Field[] att= c.getDeclaredFields();
                    o = this.singleton.get(c);
                    this.reset(request, att, o);
                    //out.print("singleton");
                }else{
                    o= c.newInstance();
                    //out.print("tsy singleton");
                }
                
                
                 
                 Object[] arguments = null;
                 
                      if(request.getParameterMap()!=null){
                        Map<String, String[]> parameter= request.getParameterMap();
                        Set<String> parameterName= parameter.keySet();
                        String[] attribute= parameterName.toArray(new String[parameterName.size()]);
                        Field[] att= o.getClass().getDeclaredFields();
                        String contentType = request.getHeader("Content-Type");
                        for(Field field : att){
                            try{
                                if(field.getType() == FileUpload.class) {
                                    if (contentType != null && contentType.startsWith("multipart/form-data")) {
                                        containsFile= true;
                                    } 
                                    if (containsFile == true) {
                                        Method methody= o.getClass().getMethod("set" + field.getName(), field.getType());
                                        Collection<Part> files = request.getParts();
                                        FileUpload file = this.fileTraitement(files, field);
                                        methody.invoke(o,file);
                                    }
                                }
                            } catch(Exception e){
                                out.println(e.getMessage());
                            }
                    }
                        this.setAttribute(request,attribute,att,o);
                        Class<?>[] parameterTypes = m.getParameterTypes();
                        
                        if(parameterTypes.length != 0){
                            arguments = new Object[parameterTypes.length];
                            Parameter[] parameters = m.getParameters();
                            int arg = 0;
                            for (Parameter parametre : parameters) {
                                String parametreName = parametre.getName();
                                for (int k = 0; k<attribute.length; k++){
                                    if(attribute[k].equals(parametreName)){
                                        arguments[arg] = cast(request, parametre, o);
                                        arg++;

                                    }
                                }
                            }
                            for (Object argument : arguments){
                                out.print(argument.getClass());
                            }
                        }
                         
                    }
                
                  if(m.isAnnotationPresent(Session.class)){
                     ArrayList<String> sessions = Collections.list(request.getSession().getAttributeNames());
                     HashMap<String, Object> session = new HashMap<String, Object>();
                     for (String attribute : sessions) {
                        Object value = request.getSession().getAttribute(attribute);
                        session.put( attribute , value );
                     }           
                    try{
                         Method sess = c.getDeclaredMethod("setsession",HashMap.class);
                         sess.invoke(o, session); 
                    }catch(Exception e){

                    }
                   
                }
                  
                 Object object=  m.invoke(o,arguments);
                 if(m.isAnnotationPresent(RestAPI.class)){
                      out.println( gson.toJson(object) );
                 }
                    if(object != null){
                        if(object.getClass() == ModelView.class)
                      {
                            ModelView mv= (ModelView) object;
                            HashMap<String,Object> data= mv.getData();
                            HashMap<String, Object> session = mv.getsession();
                            for (Map.Entry<String, Object> sess : session.entrySet()) {
                                 request.getSession().setAttribute(sess.getKey(), sess.getValue());
                            }
                            if(mv.getIsJson()==true){
                                 out.println( gson.toJson(data) );
                            }else{
                                if(data != null){
                                    for(HashMap.Entry<String,Object> d : data.entrySet()){
                                        request.setAttribute(d.getKey(),d.getValue());
                                    }
                                }
                                out.print(mv.getIsJson());
                                RequestDispatcher dispat = request.getRequestDispatcher(mv.getView());
                                dispat.forward(request,response);
                            } 
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