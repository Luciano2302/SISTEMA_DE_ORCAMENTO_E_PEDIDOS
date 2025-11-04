FROM eclipse-temurin:17-jdk-alpine 

WORKDIR /app

COPY src/ ./src/

RUN mkdir -p bin

RUN find src -name "*.java" > sources.txt
RUN javac -d bin @sources.txt

CMD ["java", "-cp", "bin", "Main"]