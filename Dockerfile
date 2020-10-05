#
# Build stage
#
FROM maven:3.6.3-openjdk-8
COPY src/ src/
COPY pom.xml .
RUN mvn clean package



#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=0 target/ target/

CMD ["java","-jar","target/kvachdockerproject-1.0-SNAPSHOT-jar-with-dependencies.jar"]
