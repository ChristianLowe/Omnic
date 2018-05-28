chmod +x build.sh
./build.sh

docker run -d --env OMNIC_KEY omnic:latest
