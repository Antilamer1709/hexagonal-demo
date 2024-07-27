# Hexagonal Architecture Demo in Spring Boot Application
This project is a demonstration of the hexagonal architecture in a Spring application. It was prepared for an online event at SoftServe, where I was a speaker. You can watch the recording [here](https://youtu.be/wYWh5rg88UE?si=oKweZPoB4xFwl3F5).

The demo is based on a real-world project that I developed at SoftServe over the course of two years. It simulates an online store. For simplicity, I have mocked some data and provided only a few endpoints. The OpenAPI specification is available at `http://localhost:8080/swagger-ui.html`.

The main goal of this demo is to showcase how to organize modules and packages and cover them with tests. The focus is on the architecture rather than the implementation details.

# Recording
[Recording on YouTube](https://youtu.be/wYWh5rg88UE?si=oKweZPoB4xFwl3F5)

# Presentation
The presentation is available [here](https://docs.google.com/presentation/d/1oSH4knk2zZosSuMuThOEIdnC5d_YnSYEJYVWEMGqLQ4/edit?usp=sharing)

# Run locally
1) Run a Postgres database from the container: ```docker run --name hexagonal_postgres -d -p 5434:5432 -e POSTGRES_USER=sa -e POSTGRES_PASSWORD=password -e POSTGRES_DB=hexagonal postgres```
2) Build it with maven to generate classes. ```mvn compile``` will do the job. (in Intellij you may need to reload maven project to find generated classes from the ```soap.hexagonal.demo..``` package)
3) Run the main class ```DemoApplication``` as any other Spring Boot application.

# Liquibase diff
To generate Liquibase diff file the next command from `./adapter/ folder`: ```mvn liquibase:diff "-Dliquibase.url=jdbc:postgresql://localhost:5434/hexagonal" "-Dliquibase.driver=org.postgresql.Driver" "-Dliquibase.username=sa" "-Dliquibase.password=password" "-Dhibernate.dialect=org.hibernate.dialect.PostgreSQLDialect"```

Pay attention that it won't work if you run it from the root folder!
