# Build a Fynd Extension using Node.js + Vue.js(vue3)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Vue.js](https://img.shields.io/badge/vuejs-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D)

[![Coverage Status][coveralls-badge]]([coveralls-url])

## Getting Started

This project outlines the development process for a Fynd extension that displays product listings for a company and its associated applications. By following this guide, you'll be able to set up the development environment, build the extension locally, and understand the testing procedures.

## Quick start
### Prerequisites
* You have fdk-cli installed [install](https://github.com/gofynd/fdk-cli)
* You have created a [partner account](https://partners.fynd.com).
* You have created a [development account](https://partners.fynd.com/help/docs/partners/testing-extension/development-acc#create-development-account) and [populated test data](https://partners.fynd.com/help/docs/partners/testing-extension/development-acc#populate-test-data) in it.
* You have created an [extension](https://partners.fynd.com) in partner panel. if not, you can follow [extension guide](https://partners.fynd.com/help/docs/partners/getting-started/create-extension) to create an extension.
* Update below environment variable value in `.env` file, This details you can get from partners panel
    - EXTENSION_API_KEY:`Extension api key`
    - EXTENSION_API_SECRET: `Extension api secret`

## Steps to Execute

* List of mandatory Services to be downloaded on your System

    1. [Java 14](https://www.java.com/en/) or higher
    2. [Maven](https://maven.apache.org/download.cgi) 
    3. [Redis](https://redis.io)
    4. [NodeJS 14](https://docs.npmjs.com/) or higher

* Clone the project : [Git link](https://github.com/gofynd/example-extension-java-vue)
* Open the Spring boot project on any IDE


### Install dependencies

**Install backend dependency**

```shell
mvn clean install
```

**Install frontend dependency**

Using yarn:
```shell
yarn install --cwd ./frontend
```
Using npm:
```shell
npm install --prefix ./frontend
```


## Local development
To start development locally you need to start tunnel on `FRONTEND_PORT` defined in .env file. To start tunnel you can use `fdk extension preview --port  <FRONTEND_PORT>`, it will provide partners panel URL  

> Before visiting partners panel URL provided by extension preview command you need to hit below command in new terminals

___Start backend server (in terminal 1)___
```shell
mvn spring-boot:run  
```
___Start frontend dev server (in terminal 2)___
```shell
cd frontend && npm run dev
```

This command will start backend and frontend server. Your frontend server will be working as watch mode and changes you make locally will be directly visible in partners panel.


## Build for production
Build frontend.

Using yarn:
```shell
cd frontend && yarn run build
```
Using npm:
```shell
cd frontend && npm run build
```



### Tests
Use the Below Controller to Test the Application :

* HealthController : Uses the Actuator Health points to check if all the resources are stable and active

        http://localhost:8080/_heathz
