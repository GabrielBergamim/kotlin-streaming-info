# ---- Build stage -------------------------------------------------------------
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /workspace
COPY . .
RUN ./mvnw clean package -DskipTests

# ---- Runtime stage -----------------------------------------------------------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Create non-root user
RUN useradd -u 10001 -r -s /sbin/nologin appuser
USER appuser

# Copy the fat JAR
COPY --from=builder /workspace/target/*.jar app.jar

EXPOSE 8080
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:+UseContainerSupport -XX:+ExitOnOutOfMemoryError"
ENV TZ=UTC

ENTRYPOINT ["java","-jar","/app/app.jar"]
