# Parking API

The main goal of this project is assess the knowledge, skills and abilities the candidate possesses in building RESTful API using Java and Spring MVC/Spring Boot.

### 📋 Prerequisites

The following items should be installed in your system:
* Java 11 or newer (full JDK not a JRE).
* Git Command Line Tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
    not there, just follow the install process here: https://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  * [VS Code](https://code.visualstudio.com)

### 🔧 Install Application

- Clone the Git repository or download the zip.

```
git clone https://github.com/
```
- IntelliJ
  - File -> Open...
  - Navigate to the folder
  - Select the pom.xml
  - Click on the `Open` button.
  - You can either build them on the command line `./mvnw generate-resources` or right click on the `parking` project then `Maven -> Generates sources and Update Folders`.
- Eclipse
  - File -> Import -> Maven -> Existing Maven Project
  - Navigate to the folder
  - Select the pom.xml
  - Then either build on the command line `./mvnw generate-resources` or using the Eclipse launcher (right click on project and `Run As -> Maven install`)

### ⚙️Run Application
Parking API is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line (it should work just as well with Java 11 or newer).

```
cd parking
./mvnw package
java -jar target/*.jar
```

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

### Working with Parking in your IDE

- IntelliJ
  -  A run configuration named `ParkingApplication` should have been created for you if you're using a recent Ultimate version. Otherwise, run the application by right clicking on the `ParkingApplication` main class and choosing `Run 'ParkingApplication'`.
- Eclipse
  - Run the application main method by right clicking on it and choosing `Run As -> Java Application`.

### 🛠️ Dependencies

* [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) - Development environment for building applications using the Java programming language.
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [Spring Boot](https://spring.io/projects/spring-boot) - Create stand-alone, production-grade Spring based applications.
* [Spring Framework](https://spring.io/projects/spring-framework) - Provides a comprehensive programming and configuration model for modern Java-based enterprise applications.
* [Spring Web](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc) - Provides basic web-oriented integration features.
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - JPA based data access layers.
* [Bean Validation](https://beanvalidation.org/) - Metadata model and API for JavaBean validation.
* [Lombok](https://projectlombok.org/) - Annotation-based Java library that allows to reduce boilerplate code.
* [PostgreSQL](https://www.postgresql.org/) - Open source object-relational database system.

### 🔩 Testing

* `POST /v1/parking` - it stores newly created access and sends ticket number back to us.
* `PUT /v1/parking/{licenseNumber}/pay` - it replaces current payment information of the ticket.
* `PUT /v1/parking/{licenseNumber}/out` - it replaces current information of the exit.
* `GET /v1/parking` - find all parking informations by license number.

[Download Postman Collection]()

### ✒️ Autor

* **Leandro Freires** - *Developer* - [LinkedIn](https://www.linkedin.com/in/leandro-freires/)

### 📄 License

The application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
