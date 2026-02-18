✈️ Airport Ride Pooling System

📌 Project Overview
This is a backend system that groups passengers into shared airport cabs while respecting:
.Seat capacity
.Luggage capacity
.Detour tolerance
.Real-time cancellations
.Performance constraints (100 RPS, <300ms latency)

The system dynamically assigns passengers to active rides or creates new rides when capacity is exceeded.

🛠 Tech Stack
Java 21
Spring Boot
Spring Data JPA
MySQL
Hibernate
Lombok
Maven
Postman (API Testing)

## Functional Requirements Implemented
- Group passengers into shared cabs.
- Respect luggage and seat constraints.
- Minimize total travel deviation.
- Ensure no passenger exceeds detour tolerance.
- Handle real-time cancellations.
- Support 10,000 concurrent users.
- Handle 100 requests per second.
- Maintain latency under 300ms.

⚙️ Setup Instructions

1️⃣ Clone Repository
```bash
git clone https://github.com/Dibyashreemallick11/airport-pooling.git
cd airport-pooling

2️⃣ Configure Database
CREATE DATABASE ride_pooling_db;

Update application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/ride_pooling_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3️⃣ Run Application
mvn clean install
mvn spring-boot:run
Application runs at:
http://localhost:8080

🚀 Performance Considerations
-Indexed foreign keys
-Avoided full table scans
-Used countByRideId instead of fetching all records
-Minimized DB calls
-Lazy loading for relationships
-Connection pooling via HikariCP








