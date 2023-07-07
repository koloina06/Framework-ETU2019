# Utilisation Framework-ETU2019

-Dans web.xml:
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
