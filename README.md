# Digiplay
Monorepository for an individual project for my portfolio in **Semester 6 - Enterprise Software.**

## Description
A web application providing movie streaming functionality, provided a user is authenticated and with an active subscription. Based on <a href="https://www.netflix.com">Netflix</a> and utilizing a microservice-based architecture. Movie files are stored and accessed through <a href="https://cloud.google.com/storage" target="_blank">Google Cloud Storage</a>. Mock payment system, subscription purchase happens based on credit card validity.

## Tech stack
The technologies used in the project can be grouped in the following manner
* **Backend project** - Digiplay Microservices
  - **Java** - Microservice architecture
  - **Spring Boot** - Java web framework
  - <a href="https://cloud.google.com/sql">Cloud SQL</a> - Database, PostgreSQL instance
  - <a href="https://cloud.google.com/storage" target="_blank">Google Cloud Storage</a> - File system
* **Frontend project** - Digiplay Client
  - **ReactJS** - Frontend framework
* **DevOps** - Infrastructure
  - **Docker** - Container builder
  - **Kubernetes** - Container manager
  - **Google Cloud Platform** - Cloud provider
* **CI/CD** - Automation
  - **GitHub Actions** - Pipeline provider
  - <a href="https://sonarcloud.io" target="_blank">SonarCloud</a> - Code Quality & Security Assurance

