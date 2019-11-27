FROM openjdk
VOLUME /tmp
COPY target/*.jar currency_conventer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/currency_conventer-0.0.1-SNAPSHOT.jar"]