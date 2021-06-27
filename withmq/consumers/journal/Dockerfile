FROM adoptopenjdk/openjdk11:jdk-11.0.1.13 as builder
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests

FROM adoptopenjdk/openjdk11:jdk-11.0.1.13
COPY --from=builder /src/target/journalconsumer-*.jar app.jar
CMD ["java","-jar","/app.jar"]
