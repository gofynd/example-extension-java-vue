# Build a Fynd Extension using Node.js + Vue.js(vue3)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Vue.js](https://img.shields.io/badge/vuejs-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D)


## Getting Started

This project outlines the development process for a Fynd extension that displays product listings for a company and its associated applications. By following this guide, you'll be able to set up the development environment, build the extension locally, and understand the testing procedures.

## Quick start
### Prerequisites
* You have fdk-cli installed globally [install](https://github.com/gofynd/fdk-cli)
* You have created a [partner account](https://partners.fynd.com).
* You have created a [development account](https://partners.fynd.com/help/docs/partners/testing-extension/development-acc#create-development-account) and [populated test data](https://partners.fynd.com/help/docs/partners/testing-extension/development-acc#populate-test-data) in it.

* List of mandatory Services to be downloaded on your System

    1. [Java 14](https://www.java.com/en/) or higher
    2. [Maven](https://maven.apache.org/download.cgi) 
    3. [Redis](https://redis.io)
    4. [NodeJS 16](https://docs.npmjs.com/) or higher


## Install Template Locally
To initialize your extension template locally, run the following command:
```shell
fdk extension init --template java-vue
```
Enter your preferred extension name and type, and you are all set.

## Local Development
To start local development, execute the following command:
```shell
fdk extension preview
```
This command will provide a partner’s panel URL where you can interact with your extension. For more information, please read this [guide](https://github.com/gofynd/fdk-cli?tab=readme-ov-file#extension-commands).


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
