FROM payara/micro:5-SNAPSHOT

RUN wget -O ./database-connector.jar http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.43/mysql-connector-java-5.1.43.jar

COPY ./build/libs/vehicle-service.war .

ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--addJars", "database-connector.jar", "--deploy", "vehicle-service.war"]
