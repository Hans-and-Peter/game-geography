FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Hans and Peter
VOLUME /tmp
ADD hans-and-peter.game.geography.jar hans-and-peter.game.geography.jar

# needed if we need a change date
# RUN sh -c 'touch /hans-and-peter.game.geography.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /hans-and-peter.game.geography.jar" ]
