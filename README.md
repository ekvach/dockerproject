All the commands must be executed in the project root folder.

to package project with reports please execute  the following command:
 
docker run -it --rm --name dockerkvahcproject -v "$(pwd)":/usr/src/kvach-maven -w /usr/src/kvach-maven maven:3.3-jdk-8 mvn clean package site

to run docker-compose please execute the following command (NOTE: this command includes implicit "docker-compose build" command):

docker-compose up