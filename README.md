# Ecomerce - gerenciamento transporte dos pedidos

 An Event driven system, using asynchronous messaging for inter-service communication. Services communicating by exchanging messages over messaging channels.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Java 8
Maven
Docker
```

### Installing

Clone the project and and set up the message broker and database with 

```
docker-compose up
```

Build and start each of the three projects by running the following comand in its  pom.xml's directory

```
maven clean install
java -jar target/{project.name}-0.0.1-SNAPSHOT.jar
```
#### How to use

##### Cria uma ordem de saída

```bash
$ curl -i -X POST -H "Content-Type:application/json" -d "{  \"destinationAdress\" : \"Vahia de Abreu\", \"productsQuantity\" : \"5\",  \"invoice\" : \"NoteFiscal\" }" http://localhost:8080/checkoutOrders/
  {"code":201,"status":"Created"}```


##### Lista todas as ordens de saída, cadastradas pelas empresas clientes
```bash
$ curl -i -X GET -H "Content-Type:application/json"  http://localhost:8080/checkoutOrders/
  
```
##### Lista todas as entregas disponiveis para as transportadores
```bash
$  curl -i -X GET -H "Content-Type:application/json"  http://localhost:8090/deliveries/
  

#### Transportadora agenda realizar entrega da ordem de saída, mudando o status de Disponível, para Solicitado Agendamento da Entrega, para Solicitação Agendada
```bash
$ curl -i -X PUT -H "Content-Type:application/json" -d "{  \"destinationAdress\" : \"Endereco destino\", \"productsQuantity\" : \"5\",  \"invoice\" : \"NoteFiscal\" ,  \"carrierName\" : \"Nome Transportadora\"}" http://localhost:8090/deliveries/{id}/scheduleShipping
  {"code":201,"status":"Created"}
```

#### Transportadora atualiza  status da entrega após realizar a entrega, passando o status para Enviado
```bash
$ curl -i -X PUT -H "Content-Type:application/json" -d "{  \"destinationAdress\" : \"Endereco destino\", \"productsQuantity\" : \"5\",  \"invoice\" : \"NoteFiscal\" ,  \"carrierName\" : \"Nome Transportadora\"}" http://localhost:8090/deliveries/{id}/scheduleShipping
  {"code":201,"status":"Created"}
```

`
## Running the tests

```
mvn test
```

## Built With

* [SringBoot](https://projects.spring.io/spring-boot/) - The web framework used
* [RabbitMQ](https://www.rabbitmq.com/) - The message broker
* [Redis](https://redis.io/) - Both front projects NoSql database
* [MySql](https://www.mysql.com/) - backend project database
* [RabbitMQ](https://www.rabbitmq.com/) - The message broker
* [Docker](https://www.docker.com/) - Container for database and message broker
* [Maven](https://maven.apache.org/) - Dependency Management
* [Java 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) - Programming language
* [Junit](http://junit.org/junit4/) - Test Framework
* [Mockito](http://site.mockito.org/) - Test Framework
* [RestAssured](http://rest-assured.io/) - Integration Test Framework

## Notes

### - que nosso serviço sempre esteja disponível, mesmo que o banco de dados fique fora do ar, não podemos depender só dele.
Como utiliza uma arquitetura de microserviços, cada micro serviço tem sua própria base, entao cada microserviço  consegue trabalhar sem depender,
por exemplo, do sistema back. O sistema foi dividio em 2 serviços, um responsavel por criar as entregas, e outro responsavel por atualiza
o status das entregas. Caso o sistema de criação de entregas esteja fora, ainda  possível mudar o status das entregas, e vice versa.
Até mesmo se o sistema principal da empresa, representado pelo ecomerce-back cair, conseguimos criar entregas e atualizar status, embora,
ao criar ou atualizar um status, esperamos uma resposta de confirmacao do sistema principal, que poderia facilmente ser tirada, foi deixada apenas por
achar que faz parte da regra do negócio.

### - ter o controle de resposta da conexão com nossas dependências, não podemos esperar horas a resposta da conexão com o banco de dados, por exemplo.
Ao utilizar o padrao de mensagens assincronas para conversar entre os sistemas, pouco importa a conexao com os outros sistemas ou o banco, uma vez que enviamos
jogamos as tasks na fila, e, quando os sistemas voltarem, vo consumir a fila seguir com o que fluxo normal. Essa demora não bloca o serviço que criou a mensagem
Tambem temos "durable messages", que fazem com que o rabbitmq nao as perca caso se encerre inesperadamente.

### - garantirmos que quando uma dependência da nossa aplicação, cair e voltar, ela consiga voltar ao ar normalmente e sem intervenção humana.
Como dito anteriormente, por trabalhar com uma arquitetura baseada em eventos, com a mensageria e rabbitmq, caso uma aplicacao caia, e vole,
ela continuar lendo a fila e seguir com o fluxo normal.

### - Preucupações não funcionais
A escolha da arquitetura, de microservicos, orientada a eventos, a própria escolha das ferramentas como o RabbitMQ, Redis, foram pensando nesse cenário de milhares de acessos 
simultaneos e outras aplicações utilizando o sistema principal da empresa.
Deixamos um banco nao relacional para cada micro servico que foi desenvolvido, justamente para que o tamamnho da base, a concorrencia com outras aplicações não interfiram na resposta para o usuario.
Quando precisamos utilizar esse sistema principal, fazemos de modo assincrono e utilizando o RabbitMQ, mandamos uma mensagem para o banco e esperamos uma resposta sem que a demora ou a falta dela, tenha grandes impactos para o usuario
