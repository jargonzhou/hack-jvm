# Pro Spring Boot 3 with Kotlin: In-Depth Guide to Best Practices for Cloud-Native and Microservices Development, 3rd Edition

- Part I. Introduction: 1-8
- Part II. Spring Cloud Data Flow: Internals: 9-15

version:
- Spring Boot: 3.2.3
- Kotlin: 2.0.20
- Spring Cloud: 2023.0.0

# 1. Spring Boot Quick Start
# 2. Spring Boot Internals

`ApplicationRunner` -> `CommandLineRunner` -> `ApplicationReadyEvent`


# 3. Spring Boot Web Development

```java
// spring-boot-starter-web
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
```

# 4. Spring Boot SQL Data Access

```java
// spring-boot-starter-jdbc
org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration

@Repository
JdbcTemplate
NaemdParameterJdbcTemplate

// R2DBC
DatabaseClient
ConnectionFactory
```

# 5. Spring Data with Spring Boot

Spring Data project
- Spring Data JDBC

```java
// spring-boot-starter-data-jdbc
@EnableJdbcRepositories

Repository<T,ID>
CrudRepository<T,ID>

// the Aggregate Root design pattern
NamingStrategy
@Table
@Column
@Query
// MyBatis
// callbacks, events
BeforeConvertCallback
AfterConvertCallback
BeforeSaveCallback<T>
AfterSaveCallback<T>
BeforeDeleteCallback
AfterDeleteCallback

@CreatedBy
@LastModifiedBy
@CreatedDate
@LastModifiedDate
```

- Spring Data JPA
```java
// spring-boot-starter-data-jpa
@EnableJpaRepository
@EnableTransactionManagement

JpaRepository<T,ID>
PagingAndSortingRepository<T,ID>
ListCrudRepository<T,ID>
ListPagingAndSortingRepository<T,ID>
QueryByExampleExecutor<T>

@Entity
@PrePersist @PreUpdate @PreRemove
@PostPersist @PostUpdate @PostRemove @PostLoad

@MappedCollection
@Enumerated
@ManyToOne @ManyToMany @OneToOne @OneToMany
@JoinColumn
```

example:
```java
@Entity
data class Card(
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id val id: UUID? = null,
    @get:NotBlank
    var comment: String? = null,
    @Enumerated(EnumType.STRING)
    @get:NotNull
    var cardType: CardType? = null,

    @ManyToOne
    @JoinColumn(name = "retro_board_id")
    @JsonIgnore
    var retroBoard: RetroBoard? = null
)

@Entity
data class RetroBoard(
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    var id: UUID? = null,
    @get:NotBlank(message = "A name must be provided")
    var name: String? = null,

    @OneToMany(mappedBy = "retroBoard")
    val cards: MutableList<Card> = mutableListOf()
)
```

- Spring Data REST
```java
// spring-boot-starter-data-rest
// spring-data-rest-hal-explorer

@RepositoryRestResource
```

# 6. Spring Data NoSQL with Spring Boot

Spring Data MongoDB
```java
// spring-boot-starter-data-mongodb
MongoDatabaseFactory
MongoTemplate
MongoOperations

@Document
MongoRepository<T,ID>
```

Spring Data Redis
```java
// spring-boot-starter-data-redis 
@EnableRedisRepositories

@RedisHash

RedisTemplate
StringRedisTemplate
HashOperation
ListOperations
ZSetOperations
// ...
```

# 7. Spring Boot Reactive

Reactive Streams
```java
Publisher<T>
Subscriber<T>
Subscription
Processor
```

Project Reactor
```java
Flux<T>
Mono<T>
```

```java
// spring-boot-starter-webflux

// spring-boot-starter-data-r2dbc
//  io.r2dbc:r2dbc-h2
//  org.postgresql:r2dbc-postgresql
```

```java
// spring-boot-starter-datamongodb-reactive
ReactiveCrudRepository<T, ID>
ReactiveBeforeConvertCallback<T>
```

# 8. Spring Boot Testing

test frameworks
- Junit 5
- AssertJ
- Hamcrest
- Mockito
- JSONassert
- JsonPath
- Spring Test
- Sprint Boot Test utilities

```java
// spring-boot-starter-test
@SpringBootTest

// mock environment
@AutoConfigurationMockMvc
MockMvc
MockMvcRequestBuilders
MockMvcResultMatchers

// mocking and spying beans
@MockBean // 3.4 @MockitoBean
Mockito
@SpyBean
```

testcontainers
```java
// org.springframework.boot:spring-boot-testcontainers
// org.testcontainers:junit-jupiter
@Testcontainers
@Container
@ServiceConnection
// org.testcontainers:postgresql 
// org.testcontainers:mongodb
```

Spring Boot slice testing
```java
@WebMvcTest
@DataJpaTest
@JdbcTest
@JsonTest JacksonTester<T>
@RestClientTest
@DataMongoTest
```

# 9. Spring Boot Security

```java
FilterChain
DelegatingFilterProxy 
FilterChainProxy
SecurityFilterChain

// spring-boot-starter-security
// org.springframework.security:spring-security-test
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration
org.springframework.boot.autoconfigure.security.SecurityDataConfiguration
UserDetailsService
UserDetailsManager
HttpSecurity
PasswordEncoder // BCrypt

@WithMockUser

AuthenticationProvider
UserSecurityDetailsService
```

Socal login:
```java
// spring-boot-starter-oauth2-client
//  security.oauth2.client.*

// spring-boot-starter-oauth2-authorization-server
//  spring.security.oauth2.authorizationserver.client.*

// spring.security.oauth2.resourceserver.*
// spring.security.oauth2.client.registration.users-client-authorization-code.*
```

# 10. Messaging with Spring Boot

spring-messaging
```java
Message
MessageChannel
// message endpoint
```

events in `ApplicationContext`
```java
ApplicationEvent
ApplicationListener
ContextRefreshedEvent
ContextStartedEvent
ContextStoppedEvent
ContextClosedEvent

// Spring Boot
ApplicationReadyEvent // ApplicationRunner, CommandLineRunner
ApplicationStartingEvent
ApplicationFailedEvent
// ...

ApplicationEventPublishAware
ApplicationEventPublisher
@EventListener
```

JMS
- running example: Apache ActiveMQ Artemis broker
```java
JmsTemplate
@JmsListener
SimpleMessageListenerContainer

// spring-boot-starter-artemis
// com.fasterxml.jackson.datatype:jackson-datatype-jsr310
MessageConverter
// spring.artemis.*
```

AMQP
- running example: RabbitMQ
```java
// spring-boot-starter-amqp
RabbitTemplate
ConnectionFactory
TopicExchange
Queue
Binding

SimpleRabbitListenerContainerFactory
@RabbitListener
@QueueBinding
@Queue
@Exchange
```

WebSockets
- STOMP(Simple Text-Oriented Messaging Protocol)
```java
// spring-boot-starter-websocket
@EnableWebSocketMessageBroker
MessageSendingOperations<T> // spring-message
WebSocketMessageBrokerConfigurer
    MessageBrokerRegistry
    StompEndpointRegistry

StompSessionHandlerAdapter
WebSocketStompClient
WebSocketClient
```

RSocket
```java
// spring-boot-starter-rsocket
RSocketRequester
@MessageMapping

RSocketServer
@RSocketExchange
RSocketServiceProxyFactory
```

# 11. Spring Boot Actuator

```java
// spring-boot-starter-actuator
// management.endpoints.web.base-path
// management.endpoints.web.exposure.[include|exclude]=<id>[,<id>]|*
// management.endpoints.web.cors.*
// management.server.*
// management.server.ssl.*
// management.endpoint.<id>.cache.time-to-live=<period-of-time>
// management.endpoint.health.*

// git
// com.gorylenko.gradle-git-properties

// custom endpoints
@Endpoint
@WebEndpoint
@JmxEndpoint
@ReadOperation
@WriteOperation
@DeleteOperation

// health indicators
// db, diskspace, cassandra, couchbase, elasticsearch, hazelcast, influxdb, jms, ldap, mail, mongo, neo4j, ping, rabbit, redis
HealthContributorRegistry
```

`/actuator`
```
/health
/info
/env
/beans
/conditions
/configprops
/heapdump
/threaddump
/mappings
/loggers
/metrics
/shutdown

# security
/auditevents
```

observability
```java
// loki-logback-appender
// datasource-micrometer-springboot

// micrometer-tracing-bridge-brave
// zipkin-reporter-brave
// micrometer-registry-prometheus

MeterRegistry
ObservationRegistry
ObservedAspect
@Observed
Counter
MappedInterceptor
```

# 12. Spring Boot Native and AOT

```java
// Gradle plugin
// org.graalvm.buildtools.native

@NestedConfigurationProperty

// custom hints
RuntimeHintsRegistrar
@RegisterReflectionForBinding
@ImportRuntimeHints
```

# 13. Spring Cloud with Spring Boot

running example
- Spring Cloud Consul
- Spring Cloud Vault
- Spring Cloud OpenFeign
- Spring Cloud Gateway
```java
// spring-cloud-starter-consulconfig
// spring-cloud-starter-consul-discovery
// spring.config.import: consul://
// spring.cloud.consul.*

// spring-cloud-starter-openfeign
@FeignClient
@EnableFeignClients

// spring-cloud-vault-config-databases
// spring-cloud-starter-vault-config
// spring.config.import: vault://
// spring.cloud.vault.*
SecretLeaseContainer

// spring-cloud-starter-gateway
// spring-cloud-starter-circuitbreaker-reactor-resilience4j
// spring.cloud.gateway.*
// predicates...
// filters...
CircuitBreaker

// spring-cloud-starter-kubernetes-client-all
// spring-cloud-starter-kubernetes-fabric8-all
// spring.config.import: kubernetes://

// x
// spring-cloud-stream
// spring-cloud-stream-binder-rabbit
// spring-integration-file
// spring-rabbit-test
// spring-cloud-streamtest-binder
```

# 14. Extending Spring Boot

```java
@Conditional
@EnableXxx

// myretro-spring-boot-starter
MyRetroAuditEvent
MyRetroAudit
MyRetroAuditOutputFormat    // JSON, TXT
MyRetroAuditIntercept       // BEFORE, AFTER, AROUND
MyRetroAuditStorage         // CONSOLE, DATABASE, FILE
MyRetroAuditFormatStrategy
MyRetroAuditFormatStrategyFactory
JsonOutputFormatStrategy
TextOutputFormatStrategy
MyRetroAuditAspect
MyRetroAuditProperties

EnableMyRetroAudit
MyRetroAuditConfiguration
EnableMyRetroAuditValueProvider // BeanFactoryPostProcessor
MyRetroAuditCondition           // Condition

// META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
// META-INF/additional-spring-configuration-metadata.json
```

# 15. Spring Boot New Projects

- Spring Modulith: https://spring.io/projects/spring-modulith
```java
// org.springframework.modulith:spring-modulith-bom:1.1.2

// package-info.java
ApplicationModules
Documenter
@TransactionalEventListener
@ApplicationModule
```

# A. Spring Boot Migration

X

# B. Spring Boot GraphQL
* https://spring.io/projects/spring-graphql
* https://github.com/graphql/graphiql
```java
// spring-boot-starter-graphql
// spring-graphql-test

// src/main/resources/graphql/schema.graphqls
// spring.graphql.graphiql.enabled=true

@QueryMapping
@MutationMapping
@Argument

@SchemaDirective
@DataFetcher
@SubscriptionMapping
@PathVariable
```

# See Also

- code: https://github.com/Apress/Pro-Spring-Boot-3-with-Kotlin-3rd-ed
  - running example
  - Project: Users App
  - Project: My Retro App

- [WebJars](https://www.webjars.org/): WebJars are client-side web libraries (e.g. jQuery & Bootstrap) packaged into JAR (Java Archive) files.
- [Micrometer](https://micrometer.io): Micrometer provides a facade for the most popular observability systems, allowing you to instrument your JVM-based application code without vendor lock-in. Think SLF4J, but for observability.
- [Spring Boot with GraalVM](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-with-GraalVM): Known GraalVM Native Image limitations.