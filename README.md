# Parking Management

This is a backend project to manage entry and exit of vehicles in a parking lot.

## Pre-requirements
This backend is made with Java 17, so you need it to run it.

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Download the version for 
your OS and set the JAVA_HOME environment variable. 

## Start up 🚀
Clone the project

```shell
cd existing_folder
git clone https://github.com/Sherguioth/parking.git
cd parking
```
Run the project using Maven, this project has a Maven Wrapper. For this, use this command:
````shell
.\mvnw spring-boot:run
````
## API
Actually this backend has an API with 4 endpoints:

### Enter vehicle

````http request
POST /api/parking/vehicle
Content-Type: application/json

{ "plate" : "AAA111", "type": "Car"}
````

### List all vehicles

```http request
GET /api/parking/vehicles
```

### Find a vehicle

```http request
GET /api/parking/vehicle/{plate}
```

### Exit a vehicle

```http request
PUT /api/parking/vehicle/{plate}
```

## Built with 🛠️
* [Spring](https://spring.io/)
* [Maven](https://maven.apache.org/)