FROM eclipse-temurin:11-jdk

WORKDIR /app

COPY .mvn/ .mvn
COPY pom.xml mvnw ./
COPY src ./src

#RUN ./mvnw clean install

CMD ["./mvnw", "clean", "spring-boot:run"]
