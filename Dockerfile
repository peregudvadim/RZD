FROM adoptopenjdk/openjdk11:alpine-jre

VOLUME /tmp
EXPOSE 8085

COPY build/libs/RZD-Interview-0.0.1-SNAPSHOT.jar rzd-app-by-sobin.jar

CMD ["java", "-jar", "rzd-app-by-sobin.jar"]
