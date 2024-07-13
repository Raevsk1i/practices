FROM amazoncorretto:17.0.3-alpine as corretto-jdk
ADD /consumer/target/consumer-0.0.1-SNAPSHOT.jar consumer.jar
ENTRYPOINT ["java","-jar","consumer.jar"]
