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
    //boolean isJson = false;
    HashMap<String, Object> session = new HashMap<>();

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

    /*public boolean getIsJson() {
        return isJson;
    }

    public void setIsJson(boolean isJson) {
        this.isJson = isJson;
    }*/
    
    
    
    public void addItem(String nom, Object valeur){
        data = new HashMap<String,Object>();
        data.put(nom, valeur);
    }

    public HashMap<String, Object> getsession() {
        return session;
    }

    public void setsession(HashMap<String, Object> session) {
        this.session = session;
    }
    
    public void addSession(String key,Object object) {
        this.session.put(key,object);
    }
    
}
