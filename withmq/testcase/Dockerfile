FROM adoptopenjdk/openjdk12:jdk-12.0.2_10-alpine as builder
ADD . /src
WORKDIR /src
RUN ./mvnw package  -DskipTests

FROM adoptopenjdk/openjdk12:jdk-12.0.2_10-alpine
COPY --from=builder /src/target/testcase-*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]
