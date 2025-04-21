FROM eclipse-temurin:17-jdk

COPY ./build/libs/preuser-service-0.0.1-SNAPSHOT.jar preuser-service.jar

ENTRYPOINT ["java", "-jar", "preuser-service.jar"]