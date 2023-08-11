FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD /target/backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar", "-Dspring.profiles.active=dev"]