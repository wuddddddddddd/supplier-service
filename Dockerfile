FROM 192.168.5.106:5000/51sole-apline-jre8-u261:v1.4.0
WORKDIR /51sole
ADD build/libs/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT java ${JAVA_OPTS} -jar app.jar
EXPOSE 8080