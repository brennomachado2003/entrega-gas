# --- Estágio 1: build ---
# Imagem com Maven + JDK 21, usada só para compilar.
# Não vai para a imagem final.
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia primeiro apenas o pom.xml
COPY pom.xml .

# Baixa as dependências.
# Se o pom.xml não mudar, o Docker pode reaproveitar essa camada em cache.
RUN mvn dependency:go-offline -B

# Agora copia o código-fonte
COPY src ./src

# Compila e gera o .jar
# -DskipTests pula os testes durante a criação da imagem
RUN mvn package -Dmaven.test.skip=true -B


# --- Estágio 2: runtime ---
# Imagem final, somente com o JRE.
FROM eclipse-temurin:21-jre
#FROM amazoncorretto:21-alpine

WORKDIR /app

# Copia somente o .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Porta utilizada pela aplicação
EXPOSE 8090

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]