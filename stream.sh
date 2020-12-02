#!/bin/bash

#Para descargar el java
sudo wget --no-cookies --no-check-certificate --header "Cookie:oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.rpm"
#instalar el java
sudo yum -y localinstall jdk-8u131-linux-x64.rpm
#Descargar streama
sudo wget https://github.com/dularion/streama/releases/download/v1.1/streama-1.1.war
#Crear carpeta
sudo mkdir /opt/streama
#copiar
sudo mv streama-1.1.war /opt/streama/streama.war
#correr
sudo java -jar /opt/streama/streama.war
# Crear carpeta
sudo mkdir /opt/streama/media
#privilegios
sudo chmod 664 /opt/streama/media
#Inicair streama start
sudo service streama start
#instalar httpd
sudo yum -y install httpd mod_ssl
#Iniciar httpd
systemctl start httpd