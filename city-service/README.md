# city-service

Um serviço (cliente) que irá se conectar no Eureka Server.


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
	<artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>

<!-- Necessario para algumas configs no bootstrap/application.yml -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

Já no código-fonte, adicionar a anotação ```@EnableDiscoveryClient``` na classe da aplicação.

No bootstrap.yml, adicionar ```spring.cloud.config.uri```, referenciando o Config Server, onde está configurado o Eureka