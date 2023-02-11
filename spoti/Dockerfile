FROM openjdk:11
COPY target/spoti-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
# ENTRYPOINT ["java","-cp","/app.jar", "com.scarnezis.spoti.SpotiApplication"]