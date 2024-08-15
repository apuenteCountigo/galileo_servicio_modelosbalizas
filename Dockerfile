FROM openjdk:17-alpine
VOLUME /tmp
ADD ./servicio-modelosbalizas.jar servicio-modelosbalizas.jar
ENTRYPOINT ["java","-jar","/servicio-modelosbalizas.jar"]