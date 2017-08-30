# config-server

Um servidor centralizando as informações, onde os clientes se conectam via HTTP e recebem suas configurações.

![ilustracao config server](https://docs.pivotal.io/spring-cloud-services/1-4/common/config-server/images/config-server-fig1.png)

Para utilizar o config server, adicionar ao pom:
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
	<artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

Já no código-fonte, adicionar a anotação ```@EnableConfigServer``` na classe da aplicação.