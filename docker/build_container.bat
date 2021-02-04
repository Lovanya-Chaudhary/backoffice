@echo off

cd ..
set base_dir=%cd%

echo "******Pulling latest code from git******"
git pull

echo "******Running Backoffice API maven build******"
call mvn clean install

echo "******Copying Backoffice API build to docker directory******"
cd %base_dir%\docker
xcopy %base_dir%\backoffice-api-core\target\backoffice-api-core-1.0.0-SNAPSHOT.jar %base_dir%\docker /y

echo "******Creating and running Backoffice API docker conatiner******"
docker stop BackofficeAPI
docker rm BackofficeAPI
docker image rm rnet-backoffice-api:latest
docker build . -t rnet-backoffice-api:latest
docker run -d --name BackofficeAPI -p 9070:9070 rnet-backoffice-api:latest