# 💳 Banking System API

A secure and extensible banking system built with **Java**, **Spring Boot** and **MySQL**. It provides RESTful APIs for user management, account transactions, and statement generation with email integration.

---

## 🚀 Features

- 🔐 JWT-Based Authentication & Authorization
- 👤 User Registration & Login
- 💰 Account Balance Inquiry
- ➕ Credit / ➖ Debit Transactions
- 🔄 Fund Transfers Between Accounts
- 🧾 Bank Statement Generation as PDF
- 📧 Email Sending with Statements
- 🛡️ Role-based Access Control (via Spring Security)
- 📦 Clean Architecture with Service & Repository Layers

---

## 🛠️ Tech Stack

| Technology        | Description                         |
|------------------|-------------------------------------|
| Java 17          | Backend Programming Language        |
| Spring Boot 3.5  | Core Framework for REST APIs        |
| Spring Security  | Authentication & Authorization (JWT)|
| Spring Data JPA  | ORM with Hibernate for DB access    |
| MySQL            | Relational Database                 |
| Jakarta Mail     | Sending transactional emails        |
| Lombok           | Reducing boilerplate code           |

---

---

## 🔐 JWT Authentication

- **Login Endpoint**: `/api/user/login`
- Send token in `Authorization` header:  
  `Authorization: Bearer <your-token>`
- Token expires based on `app.jwt-expiration` (configured in properties)

---

## 📬 Email Integration

When a user generates a statement, a **PDF is created and sent to their registered email**.

---

## 📬 Sample API Endpoints

| Method | Endpoint                            | Description                  |
|--------|-------------------------------------|------------------------------|
| POST   | `/api/user`                         | Register a new user          |
| GET    | `/api/user/balanceEnquiry`          | Check account balance        |
| POST   | `/api/user/debit`                   | Withdraw money from account  |
| POST   | `/api/user/credit`                  | Deposit money to account     |
| POST   | `/api/user/transfer`                | Transfer funds to another account |
| POST   | `/api/user/nameEnquiry`             | Get account holder's name    |

---

## 🔧 Setup Instructions

1. Clone the repository  
   `git clone https://github.com/pratham3778/Banking-System.git`

2. Create `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword

   app.jwt-secret=your-256-bit-secret-key
   app.jwt-expiration=86400000

   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=youremail@gmail.com
   spring.mail.password=yourpassword
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true

