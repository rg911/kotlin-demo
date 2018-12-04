!#/bin/bash

docker-compose down && HOST_IP_ADDR=$(ipconfig getifaddr en0) docker-compose up
