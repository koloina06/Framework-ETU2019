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
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author koloina
 */
public class Annote {
    
    public static List<Class<?>> getClassesWithAnnotation(Class<? extends Annotation> annotation) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            for (Package pack : Package.getPackages()) {
                for (Class<?> cls : getClassesInPackage(pack.getName())) {
                    if (cls.isAnnotationPresent(annotation)) {
                        classes.add(cls);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classes;
    }
    
    public static List<Class<?>> getClassesWithAnnotation2(Class<? extends Annotation> annotation,String p) throws Exception{
        List<Class<?>> classes = new ArrayList<>();
        for (Class<?> cls : getClassesInPackage(p)) {
            if (cls.isAnnotationPresent(annotation)) {
                classes.add(cls);
            }
        }
        return classes;
    }
    
    private static List<Class<?>> getClassesInPackage(String packageName) throws ClassNotFoundException, URISyntaxException, IOException {
        List<Class<?>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource.getProtocol().equals("file")) {
                classes.addAll(getClassesInDirectory(new File(resource.toURI()), packageName));
            }
        }
        return classes;
    }

    private static List<Class<?>> getClassesInDirectory(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    classes.addAll(getClassesInDirectory(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                    classes.add(Class.forName(className));
                }
            }
        }
        return classes;
    }
       
}