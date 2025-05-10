# Spring Boot Custom Starter: Greeting Starter

This is a working example of a custom Spring Boot starter that provides a greeting service. This project demonstrates how to create a custom Spring Boot starter using Java 17 and Spring Boot 3.

## Project Structure

This project consists of three modules:

1. **greeting-spring-boot-autoconfigure**: Contains the auto-configuration code for the greeting service.
2. **greeting-spring-boot-starter**: The actual starter that users will add as a dependency in their applications.
3. **greeting-spring-boot-sample**: A sample application that demonstrates how to use the starter.

## Features

- Custom greeting message configurable via properties
- Option to include current time in greetings
- Auto-configuration with conditional beans
- Spring Boot 3 compatible

## Requirements

- Java 17 or later
- Maven 3.6.0 or later
- Spring Boot 3.x

## Building the Project

To build the project, run the following command:

```bash
mvn clean install
```

This will build all three modules and install them in your local Maven repository.

## How to Integrate with a Spring Boot Application

### 1. Add the Starter Dependency

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>greeting-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Or if you're using Gradle:

```gradle
implementation 'com.example:greeting-spring-boot-starter:0.0.1-SNAPSHOT'
```

### 2. Configure the Starter (Optional)

You can customize the greeting by adding the following properties to your `application.properties` or `application.yml` file:

```properties
# application.properties
greeting.message=Hello
greeting.include-time=false
```

or

```yaml
# application.yml
greeting:
  message: Hello
  include-time: false
```

### 3. Use the GreetingService in Your Application

The `GreetingService` will be automatically available for injection in your Spring components:

```java
import com.example.greeting.autoconfigure.GreetingService;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final GreetingService greetingService;

    public MyService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void doSomething() {
        String greeting = greetingService.greet("John");
        System.out.println(greeting);
    }
}
```

## Configuration Properties

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `greeting.message` | String | "Hello" | The greeting message prefix |
| `greeting.include-time` | boolean | false | Whether to include the current time in the greeting |

## Sample Application

The sample application included in this project demonstrates how to use the greeting starter. You can run it using:

```bash
cd greeting-spring-boot-sample
mvn spring-boot:run
```

Then access the greeting endpoint at: http://localhost:8080/greet/YourName

## Running in Docker and Kubernetes

### Docker

To build and run the application in Docker:

1. Build the parent project first:
```bash
mvn clean install
```

2. Build the Docker image:
```bash
cd greeting-spring-boot-sample
docker build -t greeting-spring-boot-sample:latest .
```

3. Run the container:
```bash
docker run -p 8080:8080 greeting-spring-boot-sample:latest
```

Access the application at: http://localhost:8080/greet/YourName

### Kubernetes (Docker Desktop)

To deploy the application to Kubernetes running in Docker Desktop:

1. Build the Docker image (if not already built):
```bash
cd greeting-spring-boot-sample
docker build -t greeting-spring-boot-sample:latest .
```

2. Apply the Kubernetes configuration:
```bash
kubectl apply -f k8s.jinja.yml
```

3. Check the deployment status:
```bash
kubectl get deployments
kubectl get pods
kubectl get services
kubectl get secrets
kubectl get hpa
```

4. Access the application:
   - The service is exposed as LoadBalancer type
   - With Docker Desktop Kubernetes, it will be available at: http://localhost/greet/YourName
   - Note: The service maps port 80 to the application's port 8080

5. For secure endpoints, you'll need to provide the API key in the header:
```bash
curl -H "API-Key: SecretGreetingApiKey" http://localhost/secure-greet/YourName
```

6. To delete the deployment:
```bash
kubectl delete -f k8s.jinja.yml
```

### Kubernetes Secrets

The application is configured to use Kubernetes Secrets for sensitive configuration:

- `greeting.api-key`: Used for authenticated greetings
- `greeting.custom-message`: A special message used for authenticated users

These secrets are automatically mounted as environment variables in the pod:
- `GREETING_API_KEY`
- `GREETING_MESSAGE`

You can modify the secrets using:
```bash
kubectl create secret generic greeting-secret \
  --from-literal=greeting.api-key=YourNewSecretKey \
  --from-literal=greeting.custom-message=YourCustomMessage \
  --dry-run=client -o yaml | kubectl apply -f -
```

### Auto-scaling

The application is configured with Horizontal Pod Autoscaler (HPA) to automatically scale based on resource usage:

- Minimum replicas: 1
- Maximum replicas: 5
- CPU target utilization: 70%
- Memory target utilization: 80%

You can check the current scaling status with:
```bash
kubectl get hpa
kubectl describe hpa greeting-app-hpa
```

## How It Works

1. The autoconfigure module defines the `GreetingProperties` class that binds to the `greeting.*` properties in the application configuration.
2. It also defines a `GreetingService` that uses these properties to generate greeting messages.
3. The `GreetingAutoConfiguration` class sets up the beans when the conditions are met (e.g., when the required classes are on the classpath).
4. The starter module is an empty jar that depends on the autoconfigure module, making it easy for users to add the functionality with a single dependency.

## Creating Your Own Starter

To create your own starter based on this example:

1. Replace "greeting" with your own name
2. Define your own properties in a `*Properties` class
3. Create your service classes
4. Set up the auto-configuration with appropriate conditions
5. Create a starter module that depends on your auto-configuration module

## License

This project is licensed under the MIT License - see the LICENSE file for details. 