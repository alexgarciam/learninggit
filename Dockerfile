FROM openjdk:21
MAINTAINER agmarchena@gmail.com
COPY target/learning-1.0.2.jar learning-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/learning-1.0.0-SNAPSHOT.jar"]