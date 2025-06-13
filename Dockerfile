FROM openjdk:11-jre-slim
ARG TARGET_DIR
RUN echo $TARGET_DIR"/target/*.jar"
COPY  $TARGET_DIR/target/*.jar app.jar
CMD ["java", "-jar", "./app.jar"]
