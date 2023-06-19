# deploy
1. Corremos el comando
```
mvn clean install
```

2. Creamos un Dockerfile
```dockerfile
FROM openjdk:11
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD

ENV DB_URL=$DB_URL
ENV DB_USERNAME=$DB_USERNAME
ENV DB_PASSWORD=$DB_PASSWORD
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/augmented-anatomy-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

3. Realizamos docker build
```
docker build --build-arg DB_URL=jdbc:mysql://arnatomy-db.mysql.database.azure.com:3306/arnatomy?serverTimezone=UTC --build-arg DB_USERNAME=arnatomy_admin --build-arg DB_PASSWORD=Datacenter2023 -t arnatomy-api:1.0 .

```

prueba en local:
```
docker run -p 8080:8080 -e DB_URL=jdbc:mysql://arnatomy-db.mysql.database.azure.com:3306/arnatomy?serverTimezone=UTC -e DB_USERNAME=arnatomy_admin -e DB_PASSWORD=Datacenter2023 arnatomy-api:1.0
```

4. Push a docker registry
```
docker tag arnatomy-api:1.0 juanca0312/arnatomy-api
docker push juanca0312/arnatomy-api
```

5. Creamos el Azure Web for Containers y seleccionamos nuestro registry


old:
docker build -t augmented-anatomy-api:1.7 .
