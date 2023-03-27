# poc-load-after-start-with-redis-microservice

This is a sample microservices application built with Spring Boot that loads information into a Redis cache on startup.
The purpose of this application is to demonstrate how to integrate Redis caching into a microservices architecture.

## Prerequisites

To run this application, you need to have the following installed on your machine:

- Java 17 or later
- Redis server

## Running the Application

1. Clone this repository to your local machine.
2. Navigate to the root directory of the project.
3. Run the following command to build the application:

```bash
./mvnw clean package
```

4. Run the following command to start the application:

```bash
java -jar target/poc-load-after-start-with-redis-microservice-1.0.0.jar
```

5. Once the application is running, it will load some sample data into a Redis cache on startup. You can verify that the
   data has been loaded into Redis by running the following command:

```shell
redis-cli KEYS *
```

This should return a list of keys that were loaded into the Redis cache by the application.

## Architecture

TO-DO

## Contributing

If you would like to contribute to this project, please open a pull request with your changes. We welcome all
contributions!

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.