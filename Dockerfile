<<<<<<< HEAD
# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Christopher Sarmento <chsarmento@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# build app

RUN apk update && apk upgrade && \
    apk add --no-cache bash git openssh \
	maven 

RUN git clone https://github.com/chsarmento/autenticacao.git

WORKDIR /autenticacao

RUN mvn install

# The application's jar file
ARG JAR_FILE=target/autenticacao-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} autenticacao.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","autenticacao.jar"]

=======
# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Christopher Sarmento <chsarmento@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# build app

RUN apk update && apk upgrade && \
    apk add --no-cache bash git openssh \
	maven 

RUN git clone https://github.com/chsarmento/autenticacao.git

WORKDIR /autenticacao

RUN mvn install

# The application's jar file
ARG JAR_FILE=target/autenticacao-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} autenticacao.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","autenticacao.jar"]

>>>>>>> c041550b29f5ff6adbce4f89606891db2bf9d197
