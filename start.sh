#!/bin/bash
git pull
./mvnw clean install -Dmaven.test.skip=true
sudo service anxian-gate-admin-1.0.0-SNAPSHOT.jar start
