#!/bin/sh

for i in {1..5000} ; do
  curl localhost:8080/actuator
done
