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

🔗 APIs Integrated

🤖 OpenAI GPT-4

🎙 OpenAI Whisper (voice transcription)

🔢 Numbers API

💬 Quotes API

💾 Database

🗄 JPA/Hibernate for data persistence

⚡ Redis for caching

🐘 PostgreSQL (implied by JPA configuration)

🗂 Project Structure

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

⚙️ <b>Setup</b>

<b>Configure Environment Variables</b>

🔑 bot.token - Telegram Bot Token

🔑 gpt.token - OpenAI API Key

🔑 quote.token - Quotes API Token

<b>Database Configuration</b>

📝 Configure JPA properties

📝 Configure Redis properties

<b>Build and Run</b>
🏗 Run the Maven build and start the application

🏗 <b>Architecture</b>

The project follows a clean architecture pattern with the following components:

⚡ Commands: Implements the Command pattern for handling Telegram commands

🏭 Services: Business logic implementation

🗄 Repositories: Data access layer

🧩 Entities: Database models

📦 DTOs/Records: Data transfer objects for API communication

⚙️ Configuration: System configuration classes

📄 <b>License</b>

This project is licensed under the MIT License. See the LICENSE file for details.
Contacts

📫 <b>Get in Touch</b>

Feel free to reach out if you have questions, feedback, or want to collaborate:

👤 Name: Vladyslav Savkiv

📧 Email: sav320801@gmail.com

🔗 LinkedIn: [My LinkedIn profile](https://www.linkedin.com/in/vladyslav-savkiv/)

💻 GitHub: [My GitHub profile](https://github.com/s1zyy)

🐦 Twitter / Other: [optional link]

💬 I’m always happy to discuss this project, ideas, or opportunities for collaboration.

📝 <b>TODO</b>

🐳 Add Dockerfile for containerized deployment

📦 Add docker-compose configuration for development and production setup

<b>© 2025 Vladyslav Savkiv</b>