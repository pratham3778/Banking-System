# 🏦 Banking System - Spring Boot Project

This is a full-stack **Banking System** project built using **Java** and **Spring Boot**. The application provides core banking functionalities such as account management, fund transfers, balance enquiry, transaction history, and email notifications.

---

## 📌 Features

- ✅ User Registration and Management
- 💰 Deposit, Withdrawal, and Fund Transfer
- 📜 View Transaction History
- 🔍 Account Enquiry & Balance Check
- 📧 Email Notification Service
- 🔐 Validation and Error Handling
- 📦 RESTful API architecture

---

## 🔧 Technologies Used

| Technology     | Purpose                         |
|----------------|----------------------------------|
| Java           | Backend Programming              |
| Spring Boot    | REST API & Application Framework |
| Spring Data JPA| Database Access Layer            |
| MySQL          | Relational Database              |
| Lombok         | Reduce Boilerplate Code          |
| Maven          | Dependency Management            |
| JavaMailSender | Email Notifications              |

---

## ⚙️ How to Run

### Prerequisites:
- Java 17+
- Maven
- MySQL

## 📬 Sample API Endpoints

| Method | Endpoint                 | Description              |
|--------|--------------------------|--------------------------|
| POST   | `/api/user`              | Register a new user      |
| POST   | `/api/user/deposit`      | Deposit money            |
| POST   | `/api/user/withdraw`     | Withdraw money           |
| POST   | `/api/user/transfer`     | Transfer funds           |
| GET    | `/api/user/transactions` | View transaction history |

