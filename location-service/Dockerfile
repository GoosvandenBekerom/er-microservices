FROM payara/micro:5-SNAPSHOT

RUN wget -O ./database-connector.jar http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.43/mysql-connector-java-5.1.43.jar


COPY ./slf4j-api-1.8.0-beta2.jar .
COPY ./slf4j-simple-1.8.0-beta2.jar .
COPY ./build/libs/location-service.war .

ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--addJars", "database-connector.jar", "--addJars", "slf4j-api-1.8.0-beta2.jar", "--addJars", "slf4j-simple-1.8.0-beta2.jar", "--deploy", "location-service.war"]
