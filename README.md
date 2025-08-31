# Demo Build Tools Project

This is a simple Java project designed to help you practice and understand different build tools used in Java development:
- Maven
- Gradle 
- Ant

## Project Structure

```
demo-build-tools/
├── src/
│   ├── main/java/com/example/demo/
│   │   ├── App.java          # Main application class
│   │   └── Calculator.java   # Utility class with basic math operations
│   └── test/java/com/example/demo/
│       └── CalculatorTest.java  # Unit tests for Calculator class
├── pom.xml                   # Maven build file
├── build.gradle              # Gradle build file
├── settings.gradle           # Gradle settings file
└── build.xml                 # Ant build file
```

## Build Tools Overview

### Maven

Maven is a build automation tool used primarily for Java projects. It addresses two aspects:
- Describes how software is built
- Describes its dependencies

**Key Maven Commands:**

```bash
# Clean and build the project
mvn clean install

# Compile the source code
mvn compile

# Run tests
mvn test

# Package the application
mvn package

# Create documentation
mvn site

# Check for updates to dependencies
mvn versions:display-dependency-updates
```

### Gradle

Gradle is a build automation tool that builds upon the concepts of Apache Ant and Apache Maven and introduces a Groovy-based domain-specific language (DSL) instead of the XML used by Maven.

**Key Gradle Commands:**

```bash
# Clean and build the project
gradle clean build

# Compile the source code
gradle compileJava

# Run tests
gradle test

# Package the application
gradle jar

# Create a fat/uber jar
gradle fatJar

# Print project info (custom task)
gradle printProjectInfo

# Run the application
gradle run
```

### Ant

Apache Ant is a Java library and command-line tool that helps building software. Ant uses XML to describe the build process and its dependencies.

**Key Ant Commands:**

```bash
# Clean and build the project
ant clean jar

# Compile the source code
ant compile

# Run tests
ant test

# Create a JAR file
ant jar

# Create a fat JAR with dependencies
ant fatjar

# Run the application
ant run

# Full lifecycle: clean, compile, test, jar
ant all
```

## Differences Between Build Tools

### Maven:
- **Pros:**
  - Convention over configuration
  - Built-in dependency management
  - Rich plugin ecosystem
  - Standardized project structure
- **Cons:**
  - Less flexible
  - XML can be verbose
  - Customization can be challenging

### Gradle:
- **Pros:**
  - More concise build scripts using Groovy/Kotlin
  - More flexible than Maven
  - Great for multi-project builds
  - Performance optimizations like incremental builds and build caching
- **Cons:**
  - Steeper learning curve
  - Evolving API
  - Less standardization across projects

### Ant:
- **Pros:**
  - High flexibility
  - Fine-grained control
  - No enforced project structure
- **Cons:**
  - No built-in dependency management
  - Verbose XML configuration
  - Need to define everything explicitly
  - Less convention/standardization

## Practice Exercises

1. **Basic builds:**
   - Build the project with each tool
   - Compare the output directories and structure
   - Note the differences in command syntax and output

2. **Dependency management:**
   - Add a new dependency (like log4j or commons-lang3) in each build file
   - Observe how each tool handles dependencies

3. **Custom tasks:**
   - Add a custom task to print build information
   - Implement the same functionality in all three tools
   - Compare implementation complexity

4. **Test reporting:**
   - Run tests with each tool
   - Examine the test reports generated
   - Compare format, location, and detail level

5. **Multi-module project:**
   - Extend this project to have multiple modules
   - Configure the build tools to handle the multi-module setup

## Additional Resources

- [Maven Official Guide](https://maven.apache.org/guides/getting-started/index.html)
- [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html)
- [Apache Ant Manual](https://ant.apache.org/manual/)
