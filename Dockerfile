FROM openjdk:17-slim
WORKDIR /opt
COPY target/graphql-test.jar /opt
EXPOSE 8080
CMD ["java" ,"-jar", "graphql-test.jar"]