#!/usr/bin/env bash
mvn checkstyle:check -pl trading-api
mvn checkstyle:check -pl application
