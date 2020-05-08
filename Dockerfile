FROM openjdk:8-jdk-slim
COPY "./target/ruleta-0.0.1-SNAPSHOT.jar" "ruleta.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ruleta.jar"]