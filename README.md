🤖 <b>Telegram GPT Bot </b>

A feature-rich Telegram bot powered by GPT-4, offering conversational AI capabilities along with various utility features.

✨ Features

💬 <b>Core Functionality</b>

🤖 AI-Powered Chat: Engage in natural conversations using GPT-4

🎤 Voice Message Support: Automatically transcribes voice messages to text

💾 Conversation History: Maintains context by saving chat history

🎭 Customizable AI Personality: Adjust the bot's communication style

🛠 <b>Additional Features</b>

🎂 Birthday Reminders: Automatic birthday greetings for users

💡 Random Quotes: Get inspiring quotes with author attribution

🔢 Number Facts: Learn interesting facts about numbers

🗑️ Chat Management: Clear conversation history when needed

⚙️ <b>User Settings</b>

👤 Personalized Names: Customize how the bot addresses you

🎨 Communication Style: Adjust the bot's tone and personality

📅 Birthday Registration: Add your birthday for special greetings

💻 <b>Technical Stack 🛠 Core Technologies</b>

☕ Java 21

🌱 Spring Boot

📦 Spring Data JPA

🧩 Spring Redis

📡 Telegram Bots API

🔗 <b>APIs Integrated</b>

🤖 OpenAI GPT-4

🎙 OpenAI Whisper (voice transcription)

🔢 Numbers API

💬 Quotes API

💾 <b>Database</b>

🗄 JPA/Hibernate for data persistence

⚡ Redis for caching

🐘 PostgreSQL (implied by JPA configuration)

🗂 <b>Project Structure</b>

📜 Command Pattern for handling Telegram commands

🏗 Repository Pattern for data access

🏭 Service Layer for business logic

🧩 Redis caching for performance optimization

📜 <b>Commands</b>

/start - Initialize the bot and see available commands

/clear - Clear conversation history

/quote - Get an inspiring quote

/number - Get an interesting fact about a number

/settings - Access personalization options

/deleteUser - Remove user data from the bot

<b>Getting started🚀</b>

<b>Prerequisites</b>

Java 21+

PostgreSQL 15+

Redis 6+

Maven 3.8+

<b>Instalation</b>

<b>1. Clone the repository</b>

git clone https://github.com/s1zyy/gptTelegramBot.git

<b>2. Configure environment variables (see application.properties.example)</b>

<b>3.Run database migrations</b>

<b>4. Build and run the application</b>

./mvnw clean install

./mvnw spring-boot:run

🏗 <b>Architecture</b>

The project follows a clean architecture pattern with the following components:

⚡ Commands: Implements the Command pattern for handling Telegram commands

🏭 Services: Business logic implementation

🗄 Repositories: Data access layer

🧩 Entities: Database models

📦 DTOs/Records: Data transfer objects for API communication

⚙️ Configuration: System configuration classes

📝 <b>TODO</b>

🐳 Add Dockerfile for containerized deployment

📦 Add docker-compose configuration for development and production setup

📫 <b>Get in Touch</b>

Feel free to reach out if you have questions, feedback, or want to collaborate:

👤 Name: Vladyslav Savkiv

📧 Email: sav320801@gmail.com

🔗 LinkedIn: [My LinkedIn profile](https://www.linkedin.com/in/vladyslav-savkiv/)

💻 GitHub: [My GitHub profile](https://github.com/s1zyy)


💬 I’m always happy to discuss this project, ideas, or opportunities for collaboration.

📄 <b>License</b>

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
Contacts

<b>© 2025 Vladyslav Savkiv</b>
