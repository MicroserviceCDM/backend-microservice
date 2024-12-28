podman compose -f docker-compose-services.yaml down
git pull
./gradlew build
podman compose -f docker-compose-services.yaml up --build -d