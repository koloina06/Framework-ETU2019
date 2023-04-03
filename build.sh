rm -r testFramework/web/WEB-INF/lib
mkdir testFramework/web/WEB-INF/lib
cd Framework/build/web/WEB-INF/classes
jar -cfv framework.jar *
cp framework.jar /home/koloina/S4/Mr_Naina/projet/testFramework/web/WEB-INF/lib
rm  framework.jar
cp -R /home/koloina/S4/Mr_Naina/projet/testFramework/build/web war
cd war/web
jar -cfv ../../framework.war *
cp ../../framework.war /home/koloina/logiciels/apache-tomcat-8.5.81/webapps
