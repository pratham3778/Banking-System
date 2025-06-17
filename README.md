# 🏦 Banking System - Spring Boot Project

This is a full-stack **Banking System** project built using **Java** and **Spring Boot**. The application provides core banking functionalities such as account management, fund transfers, balance enquiry, transaction history, and email notifications.

---

## 📌 Features

- ✅ User Registration and Management
- 💰 Credit, Debit and Fund Transfer
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

| Method | Endpoint                            | Description                  |
|--------|-------------------------------------|------------------------------|
| POST   | `/api/user`                         | Register a new user          |
| GET    | `/api/user/balanceEnquiry`          | Check account balance        |
| POST   | `/api/user/debit`                   | Withdraw money from account  |
| POST   | `/api/user/credit`                  | Deposit money to account     |
| POST   | `/api/user/transfer`                | Transfer funds to another account |
| POST   | `/api/user/nameEnquiry`             | Get account holder's name    |

