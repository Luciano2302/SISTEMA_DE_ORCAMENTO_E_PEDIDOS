# Build da imagem
docker build -t sistema-orcamentos .

# Executar o container  interativamente
docker run -it sistema-orcamentos

# Build e execução
docker-compose up --build

# Ou para executar em background
docker-compose up -d --build

# Ver logs
docker-compose logs

# Parar
docker-compose down

# Fluxo de Teste Sugerido:


1. - Cadastrar Produto

2. - Cadastrar Serviço

3. - Cadastrar Cliente

4. - Listar Produtos

5. - Listar Serviços

6. - Listar Clientes

7. - Criar Orçamento

8. - Listar Orçamentos

9. - Aprovar Orçamento"

10. - Gerar Pedido

11. - Listar Pedidos

12. - Confirmar Pedido

13. - Gerar Fatura

14. - Listar Faturas

