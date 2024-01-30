# Recording
[Recording on YouTube](https://youtu.be/wYWh5rg88UE?si=oKweZPoB4xFwl3F5)

# Hexagonal demo
Demo of the hexagonal architecture in the spring application.

The project simulates an online store. For the sake of simplicity, I mocked some data and provide only a few endpoints. Take a look at http://localhost:8080/swagger-ui.html to see all of them with a description.

The main idea was to demonstrate how to organize modules and packages, and cover them with tests, so the implementations don't really matter.

# Presentation
The presentation is available by this [link](https://docs.google.com/presentation/d/1oSH4knk2zZosSuMuThOEIdnC5d_YnSYEJYVWEMGqLQ4/edit?usp=sharing)

# To run locally
1) Run a Postgres database from the container: ```docker run --name hexagonal_postgres -d -p 5434:5432 -e POSTGRES_USER=sa -e POSTGRES_PASSWORD=password -e POSTGRES_DB=hexagonal postgres```
2) Build it with maven to generate classes. ```mvn compile``` will do the job. (in Intellij you may need to reload maven project to find generated classes from the ```soap.hexagonal.demo..``` package)
3) Run the main class ```DemoApplication``` as any other Spring Boot application.

# Liquibase diff
To generate Liquibase diff file the next command from ./adapter/ folder: ```mvn liquibase:diff "-Dliquibase.url=jdbc:postgresql://localhost:5434/hexagonal" "-Dliquibase.driver=org.postgresql.Driver" "-Dliquibase.username=sa" "-Dliquibase.password=password" "-Dhibernate.dialect=org.hibernate.dialect.PostgreSQLDialect"```

Pay attention that it won't work if you run it from the root folder!
