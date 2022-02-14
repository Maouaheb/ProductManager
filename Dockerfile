FROM maven:3-openjdk-11
WORKDIR /usr/src/app
COPY pom.xml .
COPY src ./src
RUN mvn package
CMD ["java", "-jar", "./target/ProductManager-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080