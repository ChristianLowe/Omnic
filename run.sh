mvn clean compile assembly:single && docker build -t omnic . && docker run -d --env OMNIC_KEY omnic:latest
