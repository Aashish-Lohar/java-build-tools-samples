# Practical Exercises Guide

This document contains step-by-step exercises to help you understand and practice Maven, Gradle, and Ant build tools.

## Prerequisites

Ensure you have installed:
- JDK 8 or later
- Maven
- Gradle
- Ant

## Exercise 1: Basic Build and Run

### Maven

```bash
# Navigate to project directory
cd demo-build-tools

# Clean and build the project
mvn clean package

# Run the application
java -jar target/demo-build-tools-1.0-SNAPSHOT.jar
```

### Gradle

```bash
# Navigate to project directory
cd demo-build-tools

# Clean and build the project
gradle clean build

# Run the application
gradle run

# Alternative: Run the JAR
java -jar build/libs/demo-build-tools-1.0-SNAPSHOT.jar
```

### Ant

```bash
# Navigate to project directory
cd demo-build-tools

# Clean and build the project
ant clean jar

# Run the application
ant run

# Alternative: Run the JAR
java -jar ant-build/jar/demo-build-tools.jar
```

## Exercise 2: Running Tests

### Maven

```bash
# Run tests only
mvn test

# Generate detailed test reports
mvn surefire-report:report
# Open target/site/surefire-report.html to view the report
```

### Gradle

```bash
# Run tests only
gradle test

# View test reports
# Open build/reports/tests/test/index.html
```

### Ant

```bash
# Run tests only
ant test

# View test reports
# XML reports are in ant-build/test-reports/
```

## Exercise 3: Adding Dependencies

Let's add logging capabilities with Log4j.

### Maven

Edit pom.xml and add:
```xml
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.17.2</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.17.2</version>
</dependency>
```

### Gradle

Edit build.gradle and add:
```groovy
dependencies {
    implementation 'org.apache.logging.log4j:log4j-api:2.17.2'
    implementation 'org.apache.logging.log4j:log4j-core:2.17.2'
    // existing dependencies...
}
```

### Ant

For Ant, you need to:
1. Download the jars manually or use a task
2. Add them to your lib directory
3. Update the classpath in build.xml

Add to build.xml (in the get-junit target section):
```xml
<target name="get-log4j" unless="log4j.present">
    <get src="https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.17.2/log4j-api-2.17.2.jar" 
         dest="${lib.dir}/log4j-api-2.17.2.jar" />
    <get src="https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.17.2/log4j-core-2.17.2.jar" 
         dest="${lib.dir}/log4j-core-2.17.2.jar" />
</target>
```

## Exercise 4: Adding Log4j to the App

Create a log4j2.xml configuration file:

```bash
mkdir -p src/main/resources
```

Create src/main/resources/log4j2.xml:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
  <Appenders>
    <Console name="Console" target="SYSTEM_OUT">
      <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="info">
      <AppenderRef ref="Console"/>
    </Root>
  </Loggers>
</Configuration>
```

Update App.java to use logging:

```java
package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        logger.info("Application started");
        
        logger.info("Performing calculations");
        int sum = calculator.add(5, 3);
        logger.debug("5 + 3 = {}", sum);
        System.out.println("5 + 3 = " + sum);
        
        int diff = calculator.subtract(5, 3);
        logger.debug("5 - 3 = {}", diff);
        System.out.println("5 - 3 = " + diff);
        
        int product = calculator.multiply(5, 3);
        logger.debug("5 * 3 = {}", product);
        System.out.println("5 * 3 = " + product);
        
        int quotient = calculator.divide(6, 3);
        logger.debug("6 / 3 = {}", quotient);
        System.out.println("6 / 3 = " + quotient);
        
        logger.info("Application finished");
    }
}
```

## Exercise 5: Custom Tasks

### Maven

Add a custom plugin in pom.xml:

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.0.0</version>
    <executions>
        <execution>
            <id>print-info</id>
            <goals>
                <goal>java</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <mainClass>com.example.demo.App</mainClass>
    </configuration>
</plugin>
```

### Gradle

Add to build.gradle:

```groovy
task customBuild {
    dependsOn 'build'
    doLast {
        println "Custom build task completed!"
        println "Project: ${project.name}"
        println "Version: ${project.version}"
    }
}
```

### Ant

Add to build.xml:

```xml
<target name="custom-build" depends="jar">
    <echo message="Custom build task completed!" />
    <echo message="Project: ${ant.project.name}" />
</target>
```

## Exercise 6: Using Profiles/Configurations

### Maven

Add profiles to pom.xml:

```xml
<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <environment>dev</environment>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <environment>prod</environment>
        </properties>
    </profile>
</profiles>
```

Run with: `mvn package -Pprod`

### Gradle

Add to build.gradle:

```groovy
if (project.hasProperty('env') && project.getProperty('env') == 'prod') {
    println "Building for production"
} else {
    println "Building for development"
}
```

Run with: `gradle build -Penv=prod`

### Ant

Add to build.xml:

```xml
<target name="set-dev-props" unless="env">
    <property name="env" value="dev"/>
    <echo message="Environment: ${env}"/>
</target>

<target name="compile" depends="init,set-dev-props">
    <echo message="Building for ${env} environment"/>
    <!-- Existing compile task -->
</target>
```

Run with: `ant compile -Denv=prod`

## Exercise 7: Generating Documentation

### Maven

```bash
# Generate Javadoc
mvn javadoc:javadoc
```

### Gradle

Add to build.gradle:
```groovy
javadoc {
    options.encoding = 'UTF-8'
}
```

Run:
```bash
gradle javadoc
```

### Ant

Add to build.xml:
```xml
<target name="javadoc">
    <javadoc sourcepath="${src.dir}" destdir="${build.dir}/docs/api" />
</target>
```

Run:
```bash
ant javadoc
```

## Bonus Exercise: Build Process Visualization

Create a simple visualization of each build tool's process:

1. Create a text file for each build tool listing the sequence of operations
2. Time each step of the build process
3. Compare the build times across tools
4. Document your observations and which tool seems most efficient for this project
