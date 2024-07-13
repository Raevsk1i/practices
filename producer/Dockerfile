FROM amazoncorretto:17.0.3-alpine as corretto-jdk
ADD /producer/target/producer-0.0.1-SNAPSHOT.jar producer.jar
ENTRYPOINT ["java","-jar","producer.jar"]
