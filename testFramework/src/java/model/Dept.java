/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import etu2019.framework.ModelView;
import etu2019.framework.annotation.App;
import etu2019.framework.annotation.ControllerA;
import etu2019.framework.annotation.Scope;
import etu2019.framework.annotation.RestAPI;

/**
 *
 * @author koloina
 */


@ControllerA
@Scope(valeur="singleton")
public class Dept {
    String nom;
    int nombreEmp;
    int appel=1;
    
    public Dept(){
        
    }

    public Dept(String nom, int nbr){
        this.nom = nom;
        this.nombreEmp = nbr;
        this.appel += 1;
    }
    
    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public int getnombreEmp() {
        return nombreEmp;
    }

    public void setnombreEmp(int nombreEmp) {
        this.nombreEmp = nombreEmp;
    }
    
    public void setappel(int appel){
        this.appel=appel;
    }
    
    public int getappel(){
        return appel;
    }

    
    @App(url="test-dept")
    public ModelView test(){
       ModelView mv= new ModelView();
       mv.setView("testSingleton.jsp");
       mv.addItem("test", this);
       return mv;
    }
    
    @App(url="list-dept")
    @RestAPI
    public Dept[] liste(){
        Dept[] list= new Dept[2];
        list[0]= new Dept("info",5);
        list[1]= new Dept("compta",8);
        return list;
    }
}
