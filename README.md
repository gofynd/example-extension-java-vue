# Build a Fynd Extension using Java + VueJS
[![Coverage Status][coveralls-badge]]([coveralls-url])

### Built With
![Vue.js](https://img.shields.io/badge/Vue-6DA55F?style=for-the-badge&logo=react&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

[coveralls-badge]: https://coveralls.io/repos/github/gofynd/example-extension-java-vue/badge.svg?branch=main&&kill_cache=1
[coveralls-url]: https://coveralls.io/github/gofynd/example-extension-java-vue?branch=main

### Prerequisites

Before you start, make sure you have the following tools and accounts:

1. [FDK CLI](https://github.com/gofynd/fdk-cli): A command-line interface for Fynd extensions.
2. [Java 14 or higher](https://www.java.com/en/): Required to run and build the Java backend.
3. [Maven](https://maven.apache.org/download.cgi): A build tool for managing project dependencies and building the Java application.
4. [Redis](https://redis.io): An in-memory data structure store used as a database, cache, and message broker.
   
## Getting Started
* Initialize the template
```shell
$ fdk extension init --template java-vue
```

* Start a preview in platform to open a tunnel to FCP (Fynd Commerce Platform)
```shell
$ fdk extension preview
```

* Build front-end files
```shell
# Using yarn
$ cd frontend && yarn run build
# Using npm
$ cd frontend && npm run build
```

* Run the application
```bash
$ mvn clean install
$ mvn spring-boot:run  
```
  
You can visit the URL from `preview` command or [http://localhost:8080/](http://localhost:8080/) to check your extension.

### Docker Instructions

To run the application using Docker, follow these steps:

```shell
# Build the Docker image
$ docker build -t my-java-react-app .
# Run the Docker container
$ docker run -p 8080:8080 my-java-react-app 
```

### Tests
Use the Below Controller to Test the Application :

* [HealthController](/src/main/java/com/fynd/example/java/controller/HealthController.java) : Uses the Actuator Health points to check if all the resources are stable and active

        http://localhost:8080/_healthz
