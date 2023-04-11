# deploy
1. Corremos el comando
```
mvn clean install
```

2. Creamos un Dockerfile
```dockerfile
FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/augmented-anatomy-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

3. Realizamos docker build
```
docker build -t augmented-anatomy-api:1.0 .
```

4. Push a docker registry
```
docker tag augmented-anatomy-api:1.0 juanca0312/augmented-anatomy-api
docker push juanca0312/augmented-anatomy-api
```

5. Creamos el Azure Web for Containers y seleccionamos nuestro registry
