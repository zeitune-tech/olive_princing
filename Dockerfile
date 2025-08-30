FROM openjdk:17-jdk-slim

VOLUME /tmp

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Copy the jar file
COPY target/olive_insurance_pricing-*.jar app.jar

# Expose port
EXPOSE 8040

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8040/api/v1/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
