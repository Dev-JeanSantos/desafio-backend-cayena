FROM adoptopenjdk/openjdk11
EXPOSE 8090
WORKDIR /usr/src/app
ADD /target/backend-kotlin-0.0.1-SNAPSHOT.jar backend-kotlin.jar
ENTRYPOINT ["java","-jar","backend-kotlin.jar", "-Dspring.profiles.active=dev"]