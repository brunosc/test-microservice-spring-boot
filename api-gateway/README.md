# API Gateway utilizando Zuul

O objetivo do API gateway é prover um acesso simplificado para os clientes, enviando requisições para o verdadeiro servidor, como um proxy reverso. O Zuul é uma ferramenta para criação de uma API Gateway.

### Para utilizar o Zuul Gateway, é necessário: 

1. Adicionar a dependência:
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-zuul</artifactId>
	</dependency>
	
Essa dependência inclui o Ribbon (Load Balance) e Hystrix (Circuit Breaker)
	
2. Adicionar a anotação ```@EnableZuulProxy``` na classe main da aplicação.

### Chamando os serviços

Para fazer a chamada dos serviços, foi utilizado o Netflix Feign. O Feign é um cliente REST declarativo, que permite escrever chamadas REST sem código implementado. Ele é uma alternativa ao RestTemplate.

Adicionar a dependência:
	```
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-feign</artifactId>
	</dependency>
	```

Deve-se definir uma interface para cada cliente, utilizando as anotações do Feign. O Spring Cloud irá implementar em run-time o processo de chamada e resposta de cada cliente REST, fazendo o scan pelas interfaces.

Para habilitar o scan, deve-se utilizar a anotação ```@EnableFeignClients``` na classe main do projeto API Gateway. Com essa anotação, o Spring Cloud irá verificar as interfaces com a anotação ```@FeignClient(value = "spring.application.name")```, onde ```spring.application.name``` é o valor utilizado no arquivo bootstrap.yml do cliente.

Com o Feign, o Ribbon é automaticamente habilitado.

### Exemplo de chamadas:

| Cliente chama        | Zuul chama        |
| ---------------------------------------- |
| localhost:8080/city  | localhost:55698/  |
| localhost:8080/state | localhost:45782/  |

### Características do Zuul
- Alguns serviços podem ser excluídos utilizando ```zuul.ignored-services```
- É possível adicionar um prefixo com a propriedade ```zuul.prefix: /api```
	- Ficando assim a chamada: localhost:8080/api/city, localhost:8080/api/state