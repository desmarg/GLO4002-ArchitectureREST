# Contributing

[See here](CONTRIBUTING.md)

# Remises

[See here](http://projet2018.qualitelogicielle.ca/)

# ENV
The environment variable `TRADING_API_PORT` can be set, otherwise it
will default to `8181`.

# Commands

## Linting
```bash
# While in project root
mvn checkstyle:check -pl application
```

## Run project
```bash
# While in project root
./start_server.sh
```

## IDE Integration
[Here](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea)
is a jetbrains plugin for checkstyle, and
[here](https://checkstyle.org/eclipse-cs/#!/)
the eclipse one.
