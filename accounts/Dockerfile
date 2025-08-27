# Usa una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim AS build
# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app
# Instala Maven
RUN apt-get update && apt-get install -y maven
# Copia el archivo pom.xml y las dependencias a la imagen
COPY pom.xml .
# Usa Maven para descargar las dependencias sin construir el proyecto
RUN mvn dependency:go-offline
# Copia el código fuente del proyecto al contenedor
COPY src /app/src
# Compila el proyecto usando Maven
RUN mvn clean package -DskipTests
# Usa una imagen base más ligera para ejecutar la aplicación
FROM openjdk:17-jdk-slim
# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app
# Copia el archivo JAR generado del contenedor de construcción
COPY target/accounts-0.0.1-SNAPSHOT.jar app.jar
# Expone el puerto que usa la aplicación Spring Boot
EXPOSE 8080
# Comando para ejecutar la aplicación en el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]