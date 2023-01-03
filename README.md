# Sample Extension


### Built With

* [Spring Boot Framework](https://spring.io/projects/spring-boot)
* [Java 11](https://www.java.com/en/)
* [Redis](https://redis.io/)
* NodeJS 14 or higher


## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple steps :

### Prerequisites
List of mandatory Services to be downloaded on your System

    1. Java 14 or higher
    2. Mevan 
    2. Redis

### Steps to Execute

* Clone the project : [Git link](https://github.com/gofynd/example-extension-java-vue)
* Open the Spring boot project on any IDE
* Create a Configuration to run this application using Local Spring profile :
  ```-Dspring.profiles.active=local ```
* Build Front-end dist files
    ```
    cd app
    npm i 
    npm run build
    ```
* Run the application
  ```
  mvn clean
  mvn package
  mvn spring-boot:run  
  ```
* Server starts on *8080*


### Tests
Use the Below Controller to Test the Application :

* HealthController : Uses the Actuator Health points to check if all the resources are stable and active

        http://localhost:8080/_heathz
