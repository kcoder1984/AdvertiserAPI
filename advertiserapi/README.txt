# Spring Boot "Microservice" AdvertiserAPI Project

This is a Java / Maven / Spring Boot application that can be used as a starter for creating a microservice for AdvertiserAPI.

## How to Run 

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar ./target/advertiserapi-0.0.1-SNAPSHOT.jar

```

## About the Service

The service is just a advertiser CRUD REST service. It uses an in-memory database (H2) to store the data. You can call some REST endpoints defined in ```com.media.advertiserapi.rest``` on **port 8080**. They are exposed through swagger

http://localhost:8080/swagger-ui.html#/

Actuator endpoints are running on **port 8090**. They are not exposed through swagger.

### Get information about system health, configurations, etc.

```
http://localhost:8090/actuator/health
http://localhost:8090/actuator/info
```
# Questions and Comments: kunal.gupta.2002@gmail.com





