#!/usr/bin/env bash
mvn clean install
#mvn checkstyle:check
mvn exec:java -pl application
