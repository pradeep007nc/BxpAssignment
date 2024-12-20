# MetroBooking Project

MetroBooking is a full-stack web application with a **Spring Boot** backend and an **Angular** frontend. This project provides a booking system where the backend is powered by Spring Boot and the frontend is built with Angular.

## Project Structure

- **MetroBookingBackend**: Spring Boot backend
- **MetroBookingFrontend**: Angular frontend

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21 or higher** (for the Spring Boot backend)
- **Node.js** and **npm** (for the Angular frontend)
- **Maven** (for building the Spring Boot backend)
- **H2 Database** (this is embedded with Spring Boot, so no installation is required)

## Setup and Running the Application

Follow the steps below to run both the backend and frontend:

### 1. Run the Backend (Spring Boot)
    -- extract from zip 
    -- make sure necessary dependencies exist (java 21, maven)
    -- open commandLine and run mvn spring-boot:run
    -- backend is running in localhost:8080

### 2. Run the Frontend (angular)
    -- extract from zip
    -- make sure neccesary dependencies exist (ng cli, node)
    -- open commandLine and run `npm install && ng serve`
    -- Frontend is running in localhost:4200

### 3. data(json file for stations and passenger) exist on MetroBookingBackend project inside resources/static

### Key Points:
- **Backend**:
  - Uses **H2** as an embedded database, so no additional configuration is required.
  - The database is pre-populated from a JSON file.
- **Frontend**:
  - Simple Angular setup using `ng serve` after installing dependencies with `npm install`.
- **Database**:
  - No external setup for **H2** is necessary, as it is embedded within Spring Boot.
  - H2 console can be accessed at `http://localhost:8080/h2-console` with default credentials (`sa`/`sa`).

This should provide a quick and easy way to get both the backend and frontend up and running with minimal configuration.
