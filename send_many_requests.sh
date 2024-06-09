#!/bin/sh

curl localhost:8080/api/v1/customers
curl localhost:8080/api/lol
seq 5000 | xargs -n1 -P15 curl localhost:8080/actuator/
