🚴‍♂️ BikeApp - Spring Boot Application with Podman

This repository contains a Spring Boot application running inside a Podman container.

📌 Prerequisites

Ensure you have the following installed on your system:

Java 22

Maven

Podman

PostgreSQL (if using a database)

⚙️ Configuration

1️⃣ Database Configuration

This application uses PostgreSQL. Configure your database settings in application.properties or application.yml:

spring.datasource.url=jdbc:postgresql://localhost:5432/bikeapp
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

🚀 Build and Run the Application

2️⃣ Build the JAR

mvn clean package

3️⃣ Create a Podman Image

podman build -t bikeapp .

4️⃣ Run the Container

podman run -d -p 8080:8080 --name bikeapp-container \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://your-db-host:5432/bikeapp \
-e SPRING_DATASOURCE_USERNAME=your_username \
-e SPRING_DATASOURCE_PASSWORD=your_password \
bikeapp

5️⃣ Check Running Container

podman ps

6️⃣ Access the Application

Open a browser and navigate to:

http://localhost:8080

🛑 Stopping and Removing the Container

podman stop bikeapp-container
podman rm bikeapp-container

📜 Logging into the Container

To check logs:

podman logs -f bikeapp-container

To access the container shell:

podman exec -it bikeapp-container /bin/sh

🔑 Running the Application with OAuth2

If using OAuth2 authentication, ensure you configure your credentials in application.properties:

spring.security.oauth2.client.registration.github.client-id=your-client-id
spring.security.oauth2.client.registration.github.client-secret=your-client-secret

📤 Pushing to Podman Registry (Optional)

If you want to push your image to a registry:

podman tag bikeapp my-registry.com/bikeapp:latest
podman push my-registry.com/bikeapp:latest

🛠 Troubleshooting

If you encounter issues, try:

Checking logs using podman logs -f bikeapp-container

Restarting the container: podman restart bikeapp-container

Verifying Podman installation: podman info

This README.md provides a guide to running a Spring Boot application with Podman, including database setup, OAuth2, and deployment options. Adjust configurations as needed for your environment. 🚀