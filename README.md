# MadarsoftTask

MadarsoftTask is an Android application built using modern development tools and architectural patterns. The app features two primary screens:

1. **Input Screen:** Allows users to input their name, age, job title, and gender. The data is then saved locally using Room.
2. **Display Screen:** Retrieves and displays the saved user data.

The project leverages Jetpack Compose for the UI, Dagger Hilt for dependency injection, and follows the MVVM pattern with Clean Architecture principles to ensure a scalable and maintainable codebase.

## Features

- **User Input:** Capture user details including name, age, job title, and gender.
- **Local Persistence:** Save and manage user data locally using Room.
- **Modern UI:** Build responsive interfaces with Jetpack Compose.
- **Dependency Injection:** Simplify dependency management using Dagger Hilt.
- **Clean Architecture:** Maintain a clear separation of concerns with MVVM and Clean Architecture principles.

## Architecture

The project is organized into three main layers:

- **Presentation Layer:**
  - UI built with Jetpack Compose.
  - ViewModels manage UI-related data and handle user interactions.
  
- **Domain Layer:**
  - Contains business logic and use cases.
  - Defines entities and interactions that are independent of any framework.
  
- **Data Layer:**
  - Implements data sources using Room for local storage.
  - Repositories provide a clean API for data access to the domain layer.

This separation ensures that each layer remains independent and testable.

## Technologies

- **Jetpack Compose:** For building declarative UI components.
- **Dagger Hilt:** For dependency injection, streamlining the creation and management of dependencies.
- **Room Database:** For efficient local data storage.
- **MVVM Pattern:** To manage UI-related data in a lifecycle-conscious way.
- **Clean Architecture:** For a modular, maintainable, and scalable codebase.
