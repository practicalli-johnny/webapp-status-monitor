# status-monitor

[![CircleCI](https://circleci.com/gh/jr0cket/webapp-status-monitor.svg?style=svg)](https://circleci.com/gh/jr0cket/webapp-status-monitor)


A simple Status Monitor that mocks the type of information that is typically useful from several sources of monitoring systems.

The main purpose is a to demonstrate a simple server side website with Scalable Vector Graphics.

## Project Creation

The project was created with [Leiningen](https://github.com/technomancy/leiningen) 2.8.1, using the compojure template.

```bash
lein new compojure status-monitor
```

## Running

To start a web server for the application, run:

    lein ring server


## Deploying

Create an uberjar using the `lein-ring` plugin for Leiningen by using the following task

```bash
lein ring uberjar
```

Then run the application on the comand line as follows, replacing version for the generated version

```bash
java -jar target/status-monitor-version-standalone.jar
```

## License

Copyright © 2018 jr0cket

Creative Commons Attribution Share-Alike 4.0 International
