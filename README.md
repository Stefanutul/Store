# Video Game Store API

A RESTful API built with **Spring Boot** for managing products and video games in a store. Supports CRUD operations, price updates, filtering by category and age suitability, and a purchase functionality that verifies customer card balance and age.

---

## Features

- Manage general **Products** and specialized **VideoGames** (which extend Product)
- Add, retrieve, update price, and delete video games
- Filter video games by **category**
- List video games **suitable for minors** (age under 16)
- Purchase video games using a **CustomerCard** with balance and age checks
- Exception handling for insufficient balance or age restrictions
- Data initialization on application startup with sample products, video games, and customer cards

---

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- Lombok
- H2 Database (or your choice of DB)
- Maven
- JUnit 5 + Mockito (unit & integration tests)

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- IDE (IntelliJ, Eclipse, VSCode, etc.)
  The API will start on http://localhost:8081.

### Build & Run

```bash
mvn clean install
mvn spring-boot:run


