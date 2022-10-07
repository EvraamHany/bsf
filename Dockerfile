FROM openjdk:8-jdk-stretch
COPY ./target/bsffinance-0.0.1-SNAPSHOT.jar /root/bsf

WORKDIR /root/bsffinance
CMD exec java $JAVA_OPTS -jar bsffinance-0.0.1-SNAPSHOT.jar $SPRING_OPTS