rm -r testFramework/web/WEB-INF/lib
mkdir testFramework/web/WEB-INF/lib
cd Framework/build/web/WEB-INF/classes
jar -cfv framework.jar *
cp framework.jar /home/koloina/S4/Mr_Naina/projet/testFramework/web/WEB-INF/lib
rm  framework.jar
