FROM adoptopenjdk/openjdk11:alpine-jre

VOLUME /tmp
EXPOSE 8085

COPY build/libs/RZD-0.0.1-SNAPSHOT.jar rzd-app-by-peregud.jar

CMD ["java", "-jar", "rzd-app-by-peregud.jar"]
