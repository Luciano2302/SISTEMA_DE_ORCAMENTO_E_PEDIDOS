# Build da imagem
docker build -t sistema-orcamentos .

# Executar o container
docker run sistema-orcamentos

# Build e execução
docker-compose up --build

# Ou para executar em background
docker-compose up -d --build

# Ver logs
docker-compose logs

# Parar
docker-compose down

