## Kotlin Dropwizard Skeleton Project

This is a template for getting started quickly with new projects.

I will attempt to keep the dependency versions up to date.

This is by no means final and will be built out.

Feedback welcome!

### Tech Stack
- (Dropwizard)
- (Kotlin)
- Gradle (Kotlin Dsl)
- Spek (Kotlin Specification Framework) / AssertJ
- Swagger

You can import the project into IntelliJ via the build.gradle.kts file.

To run all tests and build (including the fat jar):

    ./gradlew build

To build the fat jar:

    ./gradlew fatJar

To spin up the Dropwizard application:

    ./gradlew run
    
The standard Dropwizard ports are used:

- 8080 for application resources
- 8081 admin

The OpenApi Specification (Swagger specification) is located at:

    http:localhost:8080/swagger.yaml 
    http:localhost:8080/swagger.json 