#!/bin/bash

service docker start
sleep 10
docker-compose up -d
docker-compose stop