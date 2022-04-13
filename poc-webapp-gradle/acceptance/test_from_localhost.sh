#!/bin/bash

CALCULATOR_PORT=8080
./gradlew acceptanceTest -Dcalculator.url=http://localhost:${CALCULATOR_PORT}
#test $(curl localhost:${CALCULATOR_PORT}/sum?a=1\&b=2) -eq 3
