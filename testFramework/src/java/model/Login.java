/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import etu2019.framework.ModelView;
import etu2019.framework.annotation.App;
import etu2019.framework.annotation.ControllerA;
import etu2019.framework.annotation.Scope;

/**
 *
 * @author koloina
 */

@ControllerA
@Scope(valeur="")
public class Login {
    String username;
    String password;
    
    public Login(){
    
   }

    public Login(String username, String password){
        this.username=username;
        this.password=password;
    }
    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
    
       
   @App(url="login")
   public ModelView login(){
        ModelView mv = new ModelView();
        if(this.getusername().equals("koloina") && this.getpassword().equals("mihajatiana")){
             mv.addSession("isConnected", true);
             mv.addSession("profil", "admin");
             mv.addSession("id", 1);
        }else{
            mv.addSession("isConnected", true);
            mv.addSession("profil", "user");
            mv.addSession("id", 2);
        }
        mv.setView("accueil.jsp");
        return mv;
   }
}
