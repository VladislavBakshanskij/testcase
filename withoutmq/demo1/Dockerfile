FROM adoptopenjdk/openjdk11:jdk-11.0.1.13 as builder
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests

FROM adoptopenjdk/openjdk11:jdk-11.0.1.13
COPY --from=builder /src/target/demo1-*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]
