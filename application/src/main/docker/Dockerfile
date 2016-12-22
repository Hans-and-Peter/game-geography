FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Hans and Peter
# TODO docker: get the description and final name from Maven (resource plugin)
LABEL description="Game's Geography Microservice"
# What is written by the application (Spring Boot)?
VOLUME /tmp
ADD hans-and-peter.game.geography.jar hans-and-peter.game.geography.jar

# needed if we need a change date
# RUN sh -c 'touch /hans-and-peter.game.geography.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /hans-and-peter.game.geography.jar" ]
