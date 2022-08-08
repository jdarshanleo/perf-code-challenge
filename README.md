# Upgrade - Performance Coding Challenge

## Summary

Included here, is a small backend service with REST API endpoints. This is the application-under-test.
You are required to run the service on your local machine, write performance tests for this service and report your findings along with any suggested improvements.

Keep in mind the following:
* Create a performance test project with your preferred programming language and framework, Maven (Gradle, or similar) build tool and any other non-proprietary libraries you require.
* Evaluation will be done on design, maintainability of code, coding style + best practices, application of OOD principles and extensibility of this project to add more simulations for other areas of the Upgrade application.
* Please do **Not** submit projects with JMeter files. Setup an extensible, maintainable project with Gatling or similar performance testing tools using an Object oriented programming language.

## Requirements

* Create a performance testing project, with the above guidelines in mind
* Include performance simulations to:
    * Generate 100 or more users in the database
    * Send concurrent createUser requests to the application
    * Send concurrent getUserById requests to the application
* Determine request thresholds where the application under test starts to show performance deterioration. You are free to define your own criteria for what classifies as a "service degradation".
* Similarly, determine the breaking point of the applicaton under test where the server crashes, or requests start timing out.
* Identify ways that the application under test can be improved in situations where the load is enough to cause service degradation or a crash. 
* Create a summary file with details about your test simulations, findings and analysis about performance of the application-under-test and any suggestions on improving it's performance
* Bonus points if you can utilize a postgres DB monitoring tool, to further back your findings

## Submission

Send us a zip file with your performance testing project - only include result files and source files (no class files or compiled binaries). If you have created a maven or gradle project, run mvn (or gradle) clean before archiving the project contents.

In your submission, please include the following:

* Clear instructions for running the tests that you created, using command line
* Summary file as outlined above
* Result files with some simulations you executed
* Please don't include any of your personal information in the submission

## User Service: the application-under-test

The application under test is a Java Springboot microservice which can be offers REST endpoints to create and fetch users

### System Pre-requisites

- gradle version 7.5 (not all versions are compatible, https://gradle.org/releases/)
- docker

Note: If running your application in the IDE without docker, you need to set these environment variables to connect to your database:
```
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

### Running the project

To build and run the project from the command line.

```
gradle clean build

docker-compose up --build
```

To attach to your microservice container and inspect logs, run:
```
docker logs -f user-srvc
```

To check DB, run:
```
psql -h localhost -p 5432 -d postgres -U upgrade_user
# upgrade_pass for password, when prompted

```

### URLs
The microservice will be available at http://localhost:8080
Swagger:    http://localhost:8080/swagger-ui.html
Grafana:    http://localhost:3000/
Prometheus: http://localhost:9090/

### API Endpoints

#### createUser
```
curl --location --request POST 'http://localhost:8080/user/v1/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName":"Mickey",
    "lastName":"Mouse",
    "email":"mickey@gmail.com"
}'
```

#### getUserById
```
curl --location --request GET 'http://localhost:8080/user/v1/find/<valid-uuid>' \
--data-raw ''
```

### 
