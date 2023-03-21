/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2019.framework;

import etu2019.framework.annotation.App;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.io.File;
import java.net.URL;

/**
 *
 * @author koloina
 */
public class Annote {
    
    public static List<Class<?>> GetClasses(String package_name){
		
	List<Class<?>> classes= new ArrayList<>();
            try {
		ClassLoader classLoader= Thread.currentThread().getContextClassLoader();
		Enumeration<URL> resources= classLoader.getResources(package_name);
		while(resources.hasMoreElements()) {
                    URL res= resources.nextElement();
			if(res.getProtocol().equals("file")) {
                            File packageDir= new File(res.toURI());
                            for(File file : packageDir.listFiles()) {
				String filename= file.getName();
				if(filename.endsWith(".class")) {
                                    String className= filename.substring(0, filename.length()-6);
                                    Class<?> c= Class.forName(package_name + "." + className);
                                    classes.add(c);
				}
                            }
			}
                    }
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return classes;
	}
    
    public List<Class<?>> ClassesbyPackages() {
        // Récupération de tous les packages du classpath
        Package[] packages = Package.getPackages();
        List<Class<?>> all_class= new ArrayList();
        for (Package pkg : packages) {
            String packageName = pkg.getName();
            List<Class<?>> classes= this.GetClasses(packageName);
            for (Class c : classes){
                if(c.isAnnotationPresent(App.class)){
                    all_class.add(c);
                }
            }
        }
        return all_class;
    }
    
    
}
