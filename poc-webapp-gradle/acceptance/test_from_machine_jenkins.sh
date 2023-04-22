#!/bin/bash

sleep 10
CALCULATOR_PORT=8080
CALCULATOR_IP=$(docker inspect -f "{{ .NetworkSettings.Networks.acceptance_default.IPAddress }}" poc_webapp_calculator)
./gradlew acceptanceTest -Dcalculator.url=http://${CALCULATOR_IP}:${CALCULATOR_PORT}
#test $(curl ${CALCULATOR_IP}:${CALCULATOR_PORT}/sum?a=1\&b=2) -eq 3
