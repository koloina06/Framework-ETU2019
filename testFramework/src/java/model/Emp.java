/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import etu2019.framework.ModelView;
import etu2019.framework.FileUpload;
import etu2019.framework.annotation.App;
import etu2019.framework.annotation.Auth;
import etu2019.framework.annotation.ControllerA;
import etu2019.framework.annotation.Scope;
import etu2019.framework.annotation.Session;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;


/**
 *
 * @author koloina
*/

@ControllerA
@Scope(valeur="")
public class Emp {
    int idEmp;
    String nom;
    int age;
    Date date;
    FileUpload fichier;
    int appel = 1;
    HashMap<String, Object> session = new HashMap<String, Object>();

    public void setidEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getidEmp() {
        return idEmp;
    }
    
    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public int getage() {
        return age;
    }

    public void setage(int age) {
        this.age = age;
    }
    
    public void setdate(Date date) {
        this.date = date;
    }

    public Date getdate() {
        return date;
    }
    
    public void setfichier(FileUpload file){
        this.fichier= file;
    }
    
    public FileUpload getfichier(){
        return fichier;
    }
    
    public void setappel(int appel){
        this.appel=appel;
    }
    
    public int getappel(){
        return appel;
    }

    public HashMap<String, Object> getsession() {
        return session;
    }

    public void setsession(HashMap<String, Object> sessions) {
        this.session = sessions;
    }


    public Emp(int idEmp,String nom, int age) {
        setidEmp(idEmp);
        setnom(nom);
        setage(age);
        this.appel += 1;
    }
   
    
    public Emp(){
    
    }
   
   @Auth(profil="admin")
   @App(url="emp-all")
   public ModelView  findAll(){
       Emp e1= new Emp(1,"Rabe",30);
       Emp e2= new Emp(2,"Rasoa",25);
       ArrayList<Emp> list= new ArrayList<Emp>();
       list.add(e1);
       list.add(e2);
       ModelView mv= new ModelView();
       mv.setView("test.jsp");
       mv.addItem("listEmp", list);
       return mv;
    }
   
   @Session(session="")
   @App(url="save-emp")
   public ModelView save(){
       ModelView mv= new ModelView();
       mv.setView("save.jsp");
       mv.addItem("emp" , this);
       return mv;
   }
   
   @App(url="fiche-emp")
   public ModelView fiche(String nom){
       ModelView mv= new ModelView();
       mv.setView("fiche.jsp");
       mv.addItem("fiche", nom);
       return mv;
   }
   
   @App(url="upload-emp")
   public ModelView upload(){
       ModelView mv= new ModelView();
       mv.setView("file.jsp");
       mv.addItem("fichier", fichier.getname());
       return mv;
   }
   
   @App(url="json-emp")
   public ModelView getJson(){
       Emp emp= new Emp(1,"Rabe",30);
       ModelView mv= new ModelView();
       mv.addItem("test", emp);
       mv.setIsJson(true);
       return mv;
   }

}
