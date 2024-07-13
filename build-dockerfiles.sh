#!/bin/sh
docker build -t kafka-producer:latest -f producer/Dockerfile .
docker build -t kafka-consumer:latest -f consumer/Dockerfile .
