FROM openjdk:8
EXPOSE 8100
ADD target/user-management-docker.jar user-management-docker.jar
ENTRYPOINT ["java","-jar","/user-management-docker.jar"]