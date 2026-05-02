Spring Boot CI/CD Pipeline with Docker & GitHub Actions

This project demonstrates a complete **CI/CD pipeline** for a Spring Boot application using:

-  Spring Boot (REST API)
-  PostgreSQL (Docker)
-  Docker (Containerization)
-  GitHub Actions (CI/CD)
-  Docker Hub (Image Registry)

---

 Project Overview

This is a **Task Management API** with authentication using JWT.

The CI/CD pipeline:

Push code → Build app → Create Docker image → Push to Docker Hub﻿# Spring Boot CI/CD Pipeline Project

Tech Stack
Java 21
Spring Boot
Spring Security + JWT
PostgreSQL (Docker)
Maven
Docker
GitHub Actions

How to Run Locally
1. Start PostgreSQL using Docker
docker run -d -p 5432:5432 \
-e POSTGRES_DB=taskmanagement \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=password \
--name taskdb postgres
2. Run Spring Boot App
mvn spring-boot:run
3. Test API (Example)
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d "{\"name\":\"Abhishek\",\"email\":\"abhi@example.com\",\"password\":\"password123\"}"
Docker Setup
Build Image
docker build -t taskmanagementapi .
Run Container
docker run -d -p 8080:8080 taskmanagementapi
CI/CD Pipeline (GitHub Actions)

Pipeline file:

.github/workflows/ci-cd.yml
Steps:
1. Checkout code
2. Setup Java
3. Build with Maven
4. Login to Docker Hub
5. Build Docker Image
6. Push Image to Docker Hub

GitHub Secrets

Configured secrets:

DOCKER_USERNAME
DOCKER_PASSWORD

Docker Hub

Image pushed to:

https://hub.docker.com/r/<your-username>/taskmanagementapi
Features
User Registration & Login (JWT)
Task CRUD APIs
Pagination & Sorting
Secure endpoints
Dockerized application
Automated CI/CD pipeline
CI/CD Flow
Developer push → GitHub Actions triggered →
Build → Docker Image → Push to Docker Hub
Status
 Local setup complete
 Docker working
 CI/CD pipeline successful
 Docker image pushed to Docker Hub

Author
Abhishek Gautam
