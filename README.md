# Utilisation Framework-ETU2019

1)Dans web.xml:

    .creer balise context-param :
    
        *pour mettre les packages de vos classes java
        ex: <context-param>
              <param-name>package</param-name>
              <param-value>model</param-value>
            </context-param>
            
        *ajouter les param-name(qui definiront les clé des sessions) mais les param-value seront à votre disposition :
            <context-param>
                <param-name>sessionName</param-name> 
                <param-value> </param-value> 
            </context-param>
            <context-param>
                <param-name>sessionProfil</param-name>
                <param-value> </param-value>
            </context-param>
            <context-param>
                <param-name>sessionAttribute</param-name>
                <param-value> </param-value>
            </context-param>
            
   .ajouter la balise:
   
        <servlet>
            <servlet-name>FrontServlet</servlet-name>
            <servlet-class>etu2019.framework.servlet.FrontServlet</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>FrontServlet</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>

2)Dans les classes java: 

    -importer les packages etu2019.framework.*;
    
    -annoter les classes par : @ControllerA et @Scope(valeur="" ou "singleton")
    
    -les setters et getters de la classe doivent etre set+attribut en debut minuscule tel que setidEmp
    
    -les methodes de la classe doivent etre annoté obligatoirement par @App(url="") avec l'url qui est le lien dans le jsp ex: @App(url="emp-all")
    
    -les classes retournent un modelview:
        mv.setView("save.jsp") renvoie la page jsp
        mv.addItem("emp" , this) renvoie les données à envoyer (pas obligatoire)
        
    -methodes annotés facultatifs:
        *@Auth(profil="") avec profil: la valeur que vous avez defini dans context-param profil
        ex:@Auth(profil="admin")
        => seul les utilisateurs qui ont pour profil=admin peuvent acceder à cette methode
       *@Session(session="")
          -ajouter comme attribut dans votre classe: HashMap<String, Object> session = new HashMap<String, Object>() avec son setter et son getter
          -si la methode est annoté par ceci, cela va setter la session par les sessions présents dans l'http
          
    -si la methode est de type login:
        -ajouter les sesions dans l'attribut addSession de modelview
        
    -si la methode est de type logout:
        -set l'attribut invalidateSession de modelview à true
        
    -si on veut retourner un JSON:
        soit:
           -set l'attribut isJson à true
           ou
           -annoter la methode par @RestAPI et la valeur de retour n'est pas un modelview mais zxample un tableau

3)Dans les jsp:

   -les nom des parametres doivent etre semblables au nom des attributs de la classe

4)Ajouter la librairie gson.jar
