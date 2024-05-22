FROM openjdk:8
EXPOSE 8080
ADD target/project03-0.0.1-SNAPSHOT.jar project03-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/project03-0.0.1-SNAPSHOT.jar"]