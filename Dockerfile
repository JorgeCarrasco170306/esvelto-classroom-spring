FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

# Copia los scripts y la carpeta gradle intacta
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

RUN chmod +x gradlew

# Descarga las dependencias para aprovechar el caché de capas
RUN ./gradlew dependencies --no-daemon

COPY src src

# Compilar omitiendo los tests en el build de la imagen
RUN ./gradlew bootJar -x test --no-daemon

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]