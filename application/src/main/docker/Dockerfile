FROM frolvlad/alpine-oraclejdk8:slim
# TODO docker: the base image's version is unclear. Is this reproduceable?
MAINTAINER Hans and Peter

# TODO docker: get the description and final name from Maven (resource plugin) into Docker labels.
# see https://docs.docker.com/engine/reference/builder/#/label
LABEL name="Game's Geography Microservice" \
      description="Geography is a part of our game where you occupy land, harvest and build stuff to grow an empire."

# What is written by the application (Spring Boot)?
VOLUME /tmp

ADD hans-and-peter.game.geography.jar hans-and-peter.game.geography.jar

# needed if we need a change date
# RUN sh -c 'touch /hans-and-peter.game.geography.jar'
ENV JAVA_OPTS="-Dfile.encoding=UTF-8"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /hans-and-peter.game.geography.jar" ]
