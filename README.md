# Infinite Recipe Book - Backend API

## Abstract

The Infinite Recipe Book is a specialized backend API designed to solve the common problem of "what should I cook?" when staring at a fridge full of ingredients. This Java-based RESTful API focuses on three core functionalities: ingredient data processing, AI-powered recipe generation, and meal planning for breakfast, lunch, and dinner.

The system leverages modern Java technologies including Spring Boot for the web framework, JPA/Hibernate for data persistence, PostgreSQL for database storage, and integrates with Large Language Models (LLMs) for intelligent recipe generation. The architecture follows microservices principles with clear separation of concerns, ensuring scalability and maintainability. Deployment is handled through Railway for seamless hosting and scaling.

Key features include:
- **Ingredient Data Reception**: Comprehensive ingredient validation and quantity management system for fridge inventory
- **LLM Recipe Generation**: Normalized AI responses that convert available ingredients into complete, executable recipes with meal type (breakfast/lunch/dinner) as input parameter
- **Multi-Course Meal Planning**: Intelligent meal composition including entrees, main courses, sides, and desserts when ingredient combinations allow
- **Common Household Ingredients**: Recipe generation prioritizes ingredients commonly found in most households to avoid grocery store runs
- **Ingredient Validation**: Robust validation system ensuring ingredient accuracy and quantity specifications
- **Optimized Data Structures**: Efficient algorithms for ingredient matching and recipe generation
- **High-Performance API**: RESTful endpoints designed for fast response times and scalability

The Infinite Recipe Book transforms "I have no idea what to make" into "here's exactly what you can cook," providing users with personalized recipes for any meal of the day based on what they have available, ensuring quality meal composition even with limited ingredients while using common household staples.

## Tech Stack

### Backend Framework
- **Spring Boot 3.x**: Modern Java web framework for RESTful APIs
- **Java 17+**: Latest LTS version for optimal performance

### Database & Persistence
- **PostgreSQL**: Robust relational database for recipe and ingredient data
- **JPA/Hibernate**: Object-relational mapping for database operations
- **Flyway**: Database migration management

### API & Documentation
- **Spring Web**: RESTful API endpoints
- **OpenAPI 3.0**: API documentation with Swagger UI
- **Jackson**: JSON serialization/deserialization

### AI Integration
- **OpenAI GPT-4**: Primary LLM for recipe generation
- **Fallback System**: Mock recipe generation when LLM is unavailable

### Testing
- **JUnit 5**: Unit testing framework
- **Mockito**: Mocking framework for testing
- **TestContainers**: Integration testing with PostgreSQL

### Build & Deployment
- **Maven**: Dependency management and build tool
- **Railway**: Cloud deployment platform
- **GitHub Actions**: CI/CD pipeline (optional)

### Development Tools
- **Lombok**: Reduces boilerplate code
- **Validation**: Input validation with Bean Validation
- **Logging**: SLF4J with Logback

## Development Plan

### Phase 1: Core Setup & Basic Functionality
- **Project Setup**: Maven project with essential dependencies
- **Database Design**: Simple PostgreSQL schema and core Java models
- **API Development**: Basic REST API with mock recipe generation

### Phase 2: Enhancement & Polish
- **Ingredient Validation**: Input validation and meal type categorization
- **LLM Integration**: Simple LLM integration (if budget allows) or enhanced mock recipes
- **Testing & Deployment**: Basic testing, documentation, and deployment setup

### Core Features (MVP):
- ✅ Accept ingredient list with quantities
- ✅ Categorize by meal type (breakfast/lunch/dinner)
- ✅ Generate recipe suggestions (mock or LLM)
- ✅ Return structured recipe data
- ✅ Basic error handling

### Stretch Goals:
- 🔄 Ingredient validation
- 🔄 Common household ingredients database
- 🔄 Multi-course meal suggestions
- 🔄 Railway deployment

### Key Success Criteria:
- **Phase 1 Complete**: Working API that accepts ingredients and returns recipes
- **Phase 2 Complete**: Functional system ready for production use 