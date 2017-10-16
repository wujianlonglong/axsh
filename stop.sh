#!/bin/bash
PID=$(ps -ef | grep anxian-gateway-admin-1.0.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}')
if [ -z "$PID"]
then
  echo Application is already stoped
else
  echo kill $PID
  sudo kill -9 $PID
fi
