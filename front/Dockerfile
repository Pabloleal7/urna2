# Escolha a imagem base
FROM node:16-alpine

# Cria um diretório de trabalho
WORKDIR /front

# Copia os arquivos necessários para o diretório de trabalho
COPY package.json yarn.lock ./
COPY . .

# Instala as dependências do projeto
RUN yarn install --frozen-lockfile

# Expõe a porta 5173 para acesso externo
EXPOSE 5173

# Inicia o aplicativo React com Vite
CMD ["yarn", "dev"]
