#!/bin/bash
set -eu

mvn clean package

docker run --rm \
  -p 8080:8080 \
  -v $(pwd)/target/cars.war:/usr/local/glassfish4/glassfish/domains/domain1/autodeploy/cars.war \
  glassfish:latest