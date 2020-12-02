#!/bin/bash

#instalacion de vim
sudo yum install -y vim

#Configuracion del firewall
service firewalld start

#Network manageer
service NetworkManager stop
chkconfig NetworkManager off

#asignar eth1
firewall-cmd --permanent --zone=dmz --add interface=eth1
firewall-cmd --permanent --zone=internal --add-interface=eth2
firewall-cmd --reload

#reglas
firewall-cmd --direct  --permanent --add-rule ipv4 nat POSTROUTING 0 -o eth1 -j MASQUERADE
 
firewall-cmd --direct  --permanent --add-rule ipv4 filter FORWARD 0 -i eth2 -o eth1 -j ACCEPT

firewall-cmd --direct  --permanent --add-rule ipv4 filter FORWARD 0 -i eth1 -o eth2 -m state --state RELATED,ESTABLISHED -j ACCEPT

#agregar servicio http
firewall-cmd --permanent --zone=dmz --add-service=http

#agregar protocolo
sudo firewall-cmd --zone=dmz --add-port=8080/tcp --permanent

#agregar redireccion
firewall-cmd --zone="dmz" --add-forward-port=port=8080:proto=tcp:toport=8080:toaddr=192.168.50.2 --permanent

firewall-cmd --zone="internal" --add-forward-port=port=8080:proto=tcp:toport=8080:toaddr=192.168.50.2 --permanent

firewall-cmd --permanent --zone=dmz --add-masquerade --permanent

firewall-cmd --reload