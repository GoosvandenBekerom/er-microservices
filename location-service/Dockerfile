FROM payara/micro:5-SNAPSHOT

RUN wget -O ./database-connector.jar http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.43/mysql-connector-java-5.1.43.jar

COPY ./build/libs/location-service.war .

#-Dhost=http://192.168.59.103 -Dport=8080 -DserviceRoot=snoopee-hello-service/api

ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--addJars", "database-connector.jar", "--deploy", "location-service.war",
            "-Dhost=http://localhost", "-Dport=8080", "-DserviceRoot=snoopee-location-service/api", " -DsnoopeeService: discovery:8080/snoopee-service/"]
