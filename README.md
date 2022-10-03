# Hexagonal demo
Demo of the hexagonal architecture in the spring application. 

The project simulates an online store. For the sake of simplicity, I mocked some data and provide only a few endpoints. Take a look at http://localhost:8080/swagger-ui.html to see all of them with a description.

The main idea was to demonstrate how to organize modules and packages, and cover them with tests, so the implementations don't really matter.

# Presentation
The presentation is available by this [link](https://docs.google.com/presentation/d/1oSH4knk2zZosSuMuThOEIdnC5d_YnSYEJYVWEMGqLQ4/edit?usp=sharing)

# To run locally
1) Build it with maven to generate classes. ```mvn compile``` will do the job.
2) Run the main class ```DemoApplication``` as any other Spring Boot application.
