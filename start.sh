#!/bin/bash
git pull
./mvnw clean install -Dmaven.test.skip=true
sudo service anxian-gateway-admin-1.0.0-SNAPSHOT.jar start
