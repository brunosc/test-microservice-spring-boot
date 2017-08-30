# eureka-server

O Eureka é utilizado como "Service Discovery". Fazendo uma analogia com chats, quando as pessoas (clientes) entram em um chat, eles se registram no servidor e o servidor sabe que você, e diversos outros clientes, estão on-line. Ou seja, você como cliente "descobre" outros clientes.

Alguns pontos sobre o Eureka:
- Parte do Netflix OSS
- Clientes se conectam provendo metadatas (host, porta, etc)
- Clientes enviam "heartbeats" para o Eureka, e o Eureka remove serviços sem "heartbeats".
- Deve (em produção) rodar mais de uma instância simultaneamente, em diferentes regiões.
	- Os servidores eureka se comunicam entre si, compartilhando estados. 
	- Cada um sabe a URL de outro, por meio do Config Server


Para utilizar o Eureka, adicionar ao pom:
```
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>Dalston.RELEASE</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```

E nas dependências:
```
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-eureka-server</artifactId>
</dependency>
```

Já no código-fonte, adicionar a anotação ```@@EnableEurekaServer``` na classe da aplicação.