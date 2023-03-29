/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import etu2019.framework.ModelView;
import etu2019.framework.annotation.App;
import etu2019.framework.annotation.ControllerA;


/**
 *
 * @author koloina
 */
@ControllerA
public class Dept {
    
     @App(url="dept-all")
   public ModelView  findAll(){
        ModelView mv= new ModelView();
        mv.setView("test.jsp");
        return mv;
    }
}
