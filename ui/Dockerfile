FROM node:8-alpine

WORKDIR /opt/ui
COPY . ./

RUN npm install --silent

ENTRYPOINT ["npm", "start"]