1. Beans 
A Bean is a Java object managed by the Spring IoC container.
Spring creates, manages, and injects beans automatically based on configuration or annotations.

2. Annotations 
Annotations are special markers that give metadata/instructions to Spring.
They help Spring automatically perform tasks like DI, configuration, and request handling.

3. Dependency Injection 
Dependency Injection is a design pattern where Spring automatically provides required dependencies to a class.
This removes the need to manually create objects and improves loose coupling.

4. IoC Container
IoC Container manages the lifecycle of beans — creation, injection, and destruction.
It checks if a bean already exists in ApplicationContext; if not, it creates and returns a new one.
Types:
BeanFactory → basic, lazy initialization
ApplicationContext → advanced, eager initialization, widely used in Spring Boot

5. Maven 
Maven is a build automation and dependency management tool.
It handles project lifecycle like compile, test, package, install, deploy using the pom.xml file.

6. Spring Boot Auto-Configuration 
Spring Boot Auto-Configuration automatically configures your application based on the dependencies present in the classpath.
It reduces manual setup by providing smart defaults for most features.

#annotations 

1. @Configuration
Used to mark a class as a source of bean definitions.
Spring treats the class like a Java-based configuration file and loads beans from its methods.

2. @Component
Marks a class as a Spring-managed bean.
Spring automatically detects it during component scanning and creates its object.

3. @Autowired
Used for automatic dependency injection.
Spring injects the required bean into a constructor, field, or setter without manual object creation.

4. @ConditionalOnProperty
Used in Spring Boot to enable/disable a bean based on application.properties value.
Useful for turning features ON/OFF via config flags.
Example:
@ConditionalOnProperty(name="feature.x.enabled", havingValue="true")

5. @Qualifier
Used to resolve ambiguity when multiple beans of the same type exist.
Tells Spring which exact bean to inject.

6. @Primary
When multiple beans of the same type exist, the @Primary bean gets injected by default.
Used to set a preferred bean without using @Qualifier everywhere.

7. @Scope
Controls how many objects (beans) Spring should create.
singleton (default): only one instance for the entire application
prototype: a new instance every time it is requested

8. @Bean
Used inside @Configuration class to define a bean manually.
Useful when you need full control over bean creation or third-party class configuration.
