#! /bin/bash
if [ `java --version | head -n 1 | awk '{print $2}'` != "11.0.16" ] 
then
	echo "Error: incorrect version of java. need 11.0.16"
fi
nohup java -jar target/prj2-0.0.1-SNAPSHOT.jar 2>&1 > spring.log &
if [ $? != 0 ] 
then
       echo "Error: Error in start up process of the SpringBoot Application. Please check output.log for more details"
       exit 1
fi    
echo "Info: Spring Boot Start succeded"
sleep 10

cd reactUI/db_prj2/
if [ `npm --version` != '8.15.0' ] 
then
	echo "Error: NPM is not install or version is incorrect. need npm 8.15.0 version"
fi
nohup npm start 2>&1 node.log &
