FROM openjdk:17-alpine
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE_PATH=/var/jenkins_home/workspace/ZZZ-TEST/build/dating-academia-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
