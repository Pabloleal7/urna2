FROM node:14-alpine

WORKDIR /app

COPY . /app

RUN yarn install && \
    yarn build

EXPOSE 3000

CMD ["yarn", "start"]
