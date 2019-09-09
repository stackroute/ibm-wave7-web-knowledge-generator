FROM openjdk:11

ADD ./target/web-search-services-1.0.0.jar app.jar

#WORKDIR usr/src

ENTRYPOINT ["java","-jar", "app.jar"]