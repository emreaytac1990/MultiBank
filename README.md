# MultiBank - Real-Time Finance & Stock Tracking

**MultiBank** is a modern Android application built with a robust multi-module architecture, focusing on real-time stock market data tracking. It follows the "Now in Android" (NiA) architecture style, ensuring scalability, testability, and a clear separation of concerns.

## 🚀 App Overview
MultiBank allows users to monitor live stock prices through a WebSocket-based feed. It features a clean, responsive UI built with Jetpack Compose, supporting dynamic themes (Dark/Light) and a modular design system.

---

## 🏗 Architecture
The project is built using **Clean Architecture** principles and a **Layered Multi-module** structure.

### Key Pillars:
- **Clean Architecture:** Domain layer at the center, isolated from UI and Data.
- **Unidirectional Data Flow (UDF):** State flows down, events flow up via ViewModels.
- **Dependency Injection:** Powered by **Hilt** for modularity and easy testing.
- **Modern UI:** 100% **Jetpack Compose** for building declarative interfaces.
- **Reactive Programming:** Extensive use of **Kotlin Coroutines and Flow** (specifically `StateFlow` for UI states).

---

## 📦 Module Breakdown

### 📱 App Module
- **`:app`**: The "glue" of the application. It contains the `MainActivity`, global navigation graph, and Hilt's Application class. It depends on all feature modules.

### 🧩 Feature Modules
- **`:feature:stock`**: Contains the UI logic for stock tracking. 
    - *Key components:* `FeedViewModel`, `FeedScreen`. It interacts with the domain layer to fetch and display live price feeds.

### 🛠 Core Modules (Shared Logic)
- **`:core:domain`**: Contains business logic, models, and **Repository Interfaces**. This module has no dependencies on Android or other implementation modules.
- **`:core:data`**: Implements the repositories defined in the domain layer. Handles data logic, switching between local and remote sources.
- **`:core:websocket`**: Manages real-time communication. Uses Ktor/OkHttp to handle WebSocket connections and stream live data into the app.
- **`:core:designsystem`**: The UI Kit. Contains themes (`Color.kt`, `Theme.kt`), custom components (`TopBox`, `BottomSheetMenu`), and reusable UI elements.
- **`:core:navigation`**: Centralizes navigation logic and destination definitions to avoid circular dependencies between features.
- **`:core:model`**: Pure Kotlin module containing shared data classes (e.g., `StockPrice`, `DarkThemeConfig`).
- **`:core:datastore` & `:core:datastore-proto`**: Handles local persistence for user settings and preferences using Proto DataStore.
- **`:core:di`**: Shared dependency injection utilities, such as Coroutine Dispatcher qualifiers.

### ⚙️ Build Logic
- **`:build-logic:convention`**: Custom Gradle plugins (Convention Plugins) that centralize build configuration for Android, Compose, and Hilt across all modules.

---

## 🛠 Tech Stack
- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Asynchronous Flow:** Coroutines & Flow
- **Dependency Injection:** Hilt
- **Networking/Socket:** Ktor / OkHttp
- **Local Storage:** Proto DataStore
- **Build System:** Gradle (Kotlin DSL) + Version Catalog (`libs.versions.toml`) + Convention Plugins
- **Architecture:** MVVM + Clean Architecture

---

## 💡 Key Features
- **Live Price Feed:** Real-time updates via WebSockets with seamless state management.
- **Dynamic Theming:** Supports system-default, light, and dark modes.
- **Modular Scalability:** New features can be added as independent modules without affecting existing logic.
- **Type-Safe Navigation:** Centralized navigation management for multi-module environments.

---

## 🛠 Setup & Installation
1. Clone the repository.
2. Ensure you have the latest **Android Studio**.
3. Sync Gradle (The project uses Version Catalogs for dependency management).
4. Run the `:app` module on an emulator or physical device.
