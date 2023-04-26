/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import etu2019.framework.ModelView;
import etu2019.framework.annotation.App;
import etu2019.framework.annotation.ControllerA;
import java.util.ArrayList;


/**
 *
 * @author koloina
 */
@ControllerA
public class Emp {
    String nom;
    int age;

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

    public Emp(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }
    
    public Emp(){
    
    }
        
     @App(url="emp-all")
   public ModelView  findAll(){
       Emp e1= new Emp("Rabe",30);
       Emp e2= new Emp("Rasoa",25);
       ArrayList<Emp> list= new ArrayList<Emp>();
       list.add(e1);
       list.add(e2);
        ModelView mv= new ModelView();
        mv.setView("test.jsp");
        mv.addItem("listEmp", list);
        return mv;
    }
   
    @App(url="save-emp")
   public void save(){
       System.out.println("nom: "+ nom);
       System.out.println("age: "+ age);
   }
}
