FROM payara/micro:5-SNAPSHOT

RUN wget -O ./database-connector.jar http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.43/mysql-connector-java-5.1.43.jar
#RUN wget -O ./guava.jar http://central.maven.org/maven2/com/google/guava/guava/24.1-jre/guava-24.1-jre-sources.jar

COPY account-service/build/libs/account-service.war .

ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--addJars", "database-connector.jar", "--deploy", "account-service.war"]
#ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--addJars", "database-connector.jar", "--addLibs", "guava.jar", "--deploy", "account-service.war"]