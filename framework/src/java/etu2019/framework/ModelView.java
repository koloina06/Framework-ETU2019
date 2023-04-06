/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2019.framework;

import java.util.HashMap;

/**
 *
 * @author koloina
 */
public class ModelView {
    String view;
    HashMap<String,Object> data;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    
    public void addItem(String nom, Object valeur){
        data = new HashMap<String,Object>();
        data.put(nom, valeur);
    }
    
}
