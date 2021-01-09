#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=springboot-webservice

echo "> Copy Build file"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> check current applications pid"

CURRENT_PID=$(pgrep -f1 springboot-webservice | grep jar | awk '{print $1}')

echo "> current applications pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> no application to end"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> deploy new application"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> Add execution role on $JAR_NAME"

chmod +x $JAR_NAME

echo "> Execute $JAR_NAME "

nohup java -jar \
-Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
-Dspring.profiles.active=real \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

