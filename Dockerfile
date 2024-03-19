FROM openjdk:17-jdk
LABEL authors="ruben"
COPY target/D387_sample_code-0.0.2-SNAPSHOT.jar /d387-app.jar
ENTRYPOINT ["java","-jar", "/d387-app.jar"]