# test-microservice-spring-boot
Um projeto simples, com CRUD de Cidade e Estado, para testar a criação de microserviços com Spring Cloud e Netflix OSS.

### application-config
Arquivo de configuração centralizado para os clientes, contendo informações como: Conexão com banco de dados e endereço do servidor Eureka.

### config-server
Serviço que irá obter as informações do projeto acima (application-config), onde os clientes se conectam via HTTP e recebem suas configurações.

### eureka-server
O Eureka é utilizado como "Service Discovery"

### state-service
Serviço com as operaçoes CRUD para a entidade State

### city-service
Serviço com as operaçoes CRUD para a entidade City

### api-gateway
Provê um acesso simplificado para os clientes (state e city), enviando requisições para o verdadeiro servidor, como um proxy reverso.

## Subindo o projeto

1. Subir os projetos:
	1.1. eureka-server (porta 8010)
	1.2. config-server (porta 8001)
		1.2.1. Alterar o endereço do GIT no arquivo application.yml
	
2. Acessar http://localhost:8010 (caso não tenha alterado a porta)
	2.1. Irá aparecer informações do Eureka, verificar que não contém nenhum serviço conectado
	
3. Subir os projetos:
	3.1. city-service e o state-service
	
4. Acessar novamente o Eureka e verificar que irá aparecer dois clientes conectados com portas aleatórias, por exemplo:
	4.1. state-service: localhost:42563
	4.2. city-service: localhost:55425
	4.3. Se parar esses serviços e iniciar novamente, verificar que irá utilizar outra porta.
	
5. Subir o projeto api-gateway
	5.1. Verificar o Eureka, mais um cliente conectado.
	5.2. Fazer operaçoes via POSTMAN com as URL's http://localhost:8080/state e http://localhost:8080/city
		5.2.1. Onde: **localhost:8080** é a porta utilizada pelo Zuul e **/state** e **/city** é o mapeamento realizado no api-gateway.