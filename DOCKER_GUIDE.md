# Docker Guide

## Build Docker image
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=<ImageName>:<ImageVersion>"

## Push Docker image
docker push <ImageName>:<ImageVersion>

