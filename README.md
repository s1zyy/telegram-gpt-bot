# 🤖 Telegram GPT Bot: Intelligent Conversational AI (Java & Spring Boot)

This is a feature-rich Telegram bot powered by **GPT-4**, offering advanced conversational AI capabilities, seamless **voice message support**, and utility features, all built on a robust **Java Spring Boot** backend.

## 🛠️ Technology Stack

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![OpenAI GPT-4](https://img.shields.io/badge/AI%20Core-GPT--4-4a90e2?style=for-the-badge&logo=openai&logoColor=white)](https://openai.com/)
[![Redis](https://img.shields.io/badge/Caching-Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Containerization-Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

---

## ✨ Features Overview

### 💬 Core AI Functionality
* **AI-Powered Chat:** Engage in natural conversations using the powerful **GPT-4** model.
* **Voice Message Support:** Automatically transcribes voice messages to text using **OpenAI Whisper**.
* **Conversation History:** Maintains context by saving chat history using Redis for short-term memory.
* **Customizable AI Personality:** Users can adjust the bot's communication style via settings.

### 🛠 Utility & User Settings
* **Birthday Reminders:** Automatic personalized greetings for registered users.
* **Random Quotes & Number Facts:** Integration with external APIs for additional user engagement.
* **Personalization:** Users can customize their name and communication style.
* **Chat Management:** The `/clear` command allows users to reset their conversation history.

---

## ⚙️ Backend Architecture

The project follows a **Clean Architecture** approach with strong separation of concerns:

| Component | Pattern/Technology | Role |
| :--- | :--- | :--- |
| **Command Handling** | **Command Pattern** | Dedicated classes for processing each Telegram command (`/start`, `/clear`, etc.). |
| **Business Logic** | **Service Layer** | Contains core logic for AI interaction and feature execution. |
| **Data Access** | **Repository Pattern (JPA)** | Manages persistent data using Spring Data JPA (PostgreSQL). |
| **Performance** | **Redis Caching** | Used for fast access to frequently needed data and conversation state. |

---

## 🚀 Getting Started

The recommended way to run this bot is by using **Docker Compose** for a quick and repeatable setup that includes the PostgreSQL database.

### Prerequisites
* Docker & Docker Compose
* Git

### Setup & Configuration

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/s1zyy/telegram-gpt-bot.git](https://github.com/s1zyy/telegram-gpt-bot.git)
    cd telegram-gpt-bot
    ```
2.  **API Keys:** Configure your environment variables for **Telegram** and **OpenAI**. Create an `.env` file or adjust the `application.properties` (or `application.yaml`) with your tokens.
    * `telegram.bot.token`
    * `openai.api.key`

3.  **Run with Docker Compose:**
    ```bash
    docker-compose up --build
    ```
    This command builds the Spring Boot application, runs database migrations, and starts the bot and PostgreSQL containers.

---

## 📜 Available Commands

| Command | Description |
| :--- | :--- |
| `/start` | Initialize the bot and see available commands. |
| `/clear` | Clear your current conversation history and reset context. |
| `/quote` | Get an inspiring quote from an external API. |
| `/number <num>` | Get an interesting fact about a specific number. |
| `/settings` | Access personalization options (name, style, birthday). |
| `/deleteUser` | Remove all user data from the bot's database. |

---

## 🛣️ Future Enhancements (Roadmap)

* **Payment Integration:** Implement subscription logic for premium GPT models.
* **Advanced State Management:** Migrate Redis session management to a dedicated service for better scaling.
* **Monitoring:** Implement health checks and basic metrics using Micrometer/Prometheus.

---

## 👤 Author

**Vladyslav Savkiv** – Full-Stack (Java/iOS) Developer

* [LinkedIn Profile](https://www.linkedin.com/in/vladyslav-savkiv/)
* [GitHub Profile](https://github.com/s1zyy)

---