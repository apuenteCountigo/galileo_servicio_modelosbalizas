FROM openjdk:17-alpine
VOLUME /tmp
ADD /home/mome/galileo-newdev/modelosbalizas/servicio-modelosbalizas.jar servicio-modelosbalizas.jar
ENTRYPOINT ["java","-jar","/servicio-modelosbalizas.jar"]