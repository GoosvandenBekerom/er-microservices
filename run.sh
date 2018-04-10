#!/bin/bash

docker-compose down
./gradlew clean build
docker-compose build
docker-compose up
