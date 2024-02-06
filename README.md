# DailyNeeds
To calculate Discounts on Order bill

This project is a simple Java - spring boot application for calculating discount on a bill based on the User Type

1.	If the user is an employee of the store, he gets a 30% discount
2.	If the user is an affiliate of the store, he gets a 10% discount
3.	If the user has been a customer for over 2 years, he gets a 5% discount.
4.	For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5.	The percentage based discounts do not apply on groceries.
6.	A user can get only one of the percentage based discounts on a bill.


## UML Digram

- 

## Requirements

For building and running the application you need:

- [JDK 1.17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)
- Integrated Development Environment (IDE): IntellijIDEA

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `DailyneedsApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvnw spring-boot:run
```

## Running the test cases and generating coverage report
- Right click on the project folder, click on Run all test cases with coverage to run the JUnit tests and coverage report

## static code analysis such as linting
- SonarLint plugin is used to analyze the code for any issues in code and fix them

### Build an executable JAR
You can run the application from the command line using:
```
mvnw spring-boot:run
```
Or you can build a single executable JAR file that contains all the necessary dependencies, classes, and resources with:
```
mvnw clean package
```
Then you can run the JAR file with:
```
java -jar target/*.jar
```
