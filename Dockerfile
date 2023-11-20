FROM maven:3.8.4 AS builder
WORKDIR /usr/src/myapp
COPY . .
RUN mvn clean install

FROM openjdk
WORKDIR /usr/src/myapp
COPY ./target/product.jar /usr/src/myapp/product.jar
CMD ["java","-jar","product.jar"]
EXPOSE 8080
