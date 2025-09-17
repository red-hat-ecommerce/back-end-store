# Red Hat E-commerce Store Backend

A backend microservice for the Red Hat E-commerce platform that provides store location information via a RESTful API. This service is built with Quarkus and Apache Camel.

## Technologies Used

- [Quarkus](https://quarkus.io/) - A Kubernetes Native Java stack
- [Apache Camel](https://camel.apache.org/) - Integration framework
- Java 21
- Maven
- Docker

## Prerequisites

- JDK 21+
- Maven 3.8.1+
- Docker (optional, for containerized deployment)

## Getting Started

### Building the Application

```bash
# Clone the repository
git clone https://github.com/your-org/red-hat-ecommerce-2.git
cd red-hat-ecommerce-2/back-end-store

# Build the application
./mvnw clean package
```

### Running in Development Mode

```bash
./mvnw quarkus:dev
```

This will start the application in development mode with hot reload enabled.

### Running Tests

```bash
./mvnw test
```

## API Documentation

The service exposes the following REST endpoints:

### Get All Stores

```
GET /api/store/
```

**Response:**
```json
[
  {
    "storeId": "jakarta",
    "storeName": "Jakarta"
  },
  {
    "storeId": "bandung",
    "storeName": "Bandung"
  },
  {
    "storeId": "medan",
    "storeName": "Medan"
  },
  {
    "storeId": "jogjakarta",
    "storeName": "Jogjakarta"
  }
]
```

## Deployment

### JVM Mode

Build the application:
```bash
./mvnw package
```

Build the Docker image:
```bash
docker build -f Dockerfile.jvm -t redhat-ecommerce/back-end-store-jvm .
```

Run the container:
```bash
docker run -i --rm -p 8080:8080 redhat-ecommerce/back-end-store-jvm
```

### Native Mode

Build the native executable:
```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

Build the Docker image:
```bash
docker build -f Dockerfile.native-micro -t redhat-ecommerce/back-end-store-native .
```

Run the container:
```bash
docker run -i --rm -p 8080:8080 redhat-ecommerce/back-end-store-native
```

## Configuration

The application can be configured through the `application.properties` file:

- `quarkus.http.port`: HTTP port (default: 8080)
- `quarkus.management.port`: Management port for health probes (default: 9000)

## Health Checks

Health and readiness probes are available at:
```
http://localhost:9000/q/health
http://localhost:9000/q/health/ready
http://localhost:9000/q/health/live
```

## License

This project is licensed under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

## Author

Muhammad Edwin (edwin@redhat.com)
