# -------- STAGE 1: BUILD --------
FROM eclipse-temurin:25-jdk-jammy AS builder

WORKDIR /build

# Copiar Maven Wrapper
COPY mvnw .
COPY .mvn .mvn

# Dar permisos de ejecución
RUN chmod +x mvnw

# Copiamos primero pom para cache de dependencias
COPY pom.xml .
RUN ./mvnw -q -e -DskipTests dependency:go-offline

# Copiamos el resto del proyecto
COPY src ./src

# Compilamos
RUN ./mvnw clean package -DskipTests

# -------- STAGE 2: RUNTIME --------
FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

# Crear usuario no privilegiado
RUN groupadd -r spring && useradd -r -g spring spring

# Copiamos solo el jar generado
COPY --from=builder /build/target/*.jar app.jar

# Cambiar ownership del jar
RUN chown spring:spring app.jar

# Cambiar a usuario no privilegiado
USER spring:spring

EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]