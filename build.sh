testFrameWar=/home/koloina/logiciels/apache-tomcat-8.5.81/webapps/testFrame.war
tomcatTestFrame=/home/koloina/logiciels/apache-tomcat-8.5.81/webapps/testFrame
projetFramework=/home/koloina/S4/Mr_Naina/projet/projetFramework
testFramework=/home/koloina/S4/Mr_Naina/projet/projetFramework/testFramework
testFrameworkWeb=/home/koloina/S4/Mr_Naina/projet/projetFramework/testFramework/build/web
rm -r $testFrameWar
rm -r $tomcatTestFrame
rm -r testFramework/web/WEB-INF/lib
mkdir testFramework/web/WEB-INF/lib
mkdir temp
cd temp
mkdir WEB-INF
mkdir WEB-INF/classes
mkdir WEB-INF/lib
cd ../Framework/build/web/WEB-INF/classes
jar -cfv framework.jar *
cp framework.jar $testFramework/web/WEB-INF/lib/
cp framework.jar $projetFramework/temp/WEB-INF/lib/
rm  framework.jar
cd $projetFramework
cp $testFrameworkWeb/*.jsp temp
cp -r $testFrameworkWeb/WEB-INF/classes/* temp/WEB-INF/classes/
cp $testFrameworkWeb/WEB-INF/web.xml temp/WEB-INF/
cd temp
jar -cfv testFrame.war *
cp testFrame.war $testFrameWar
rm -r ../temp


