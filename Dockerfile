# Stage 1: Build the Maven application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build and package the WAR file (skipping tests)
RUN mvn clean package -DskipTests

# Stage 2: Run the application in Tomcat
FROM tomcat:10.1-jdk21-temurin-jammy

# Remove default Tomcat webapps to avoid conflicts
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the compiled WAR file to the ROOT webapp directory in Tomcat
COPY --from=build /app/target/LoomAndLuxe.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat default port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
