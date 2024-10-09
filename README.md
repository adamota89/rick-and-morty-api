# Rick and Morty : Clean Architecture

The purpose oh this repo is to follow up Clean Architecture principles in the rick and morty application.

## Project
The purpose of the Rick and Morty application is to retrieve data from the Rick and Morty API in order to display the list of characters from the cartoon and their characteristics

## Definition 
`Clean Architecture` aims to reduce the dependencies of your business logic on the services you consume (API, Database, Framework, third-party libraries), to maintain a stable application during its developments, its tests but also during changes or updates to external resources.

## Clean Architecture - MVI
![CleanArchi](https://github.com/user-attachments/assets/053ac861-8aa6-4942-b3f0-55387a61a84d)

## Clean Architecture - Layers
- `Domain Layer`: Contains the business logic and use cases, independent of other layers.
- `Data Layer`: Manages data sources (APIs, local databases) and implements repository patterns to decouple the domain from external data sources.
- `Presentation Layer`: Manages the UI and interacts with the user, following the MVVM or MVI pattern.

## Clean Architecture - MVVM UI Layer and Data Layer
![image](https://github.com/user-attachments/assets/e64bfee2-cb62-4643-b071-73786d370bda)

## Local Development 
- `build.gradle *(project)*` : Global project configuration
- `build.gradle *(Module)*` : Specific configutation *(ex : Dependencies, external librairies ...)*

## Technologies Used

- **Kotlin**: The main programming language used for building the application.
- **Jetpack Compose**: A modern Android UI toolkit used for building declarative UI components.
- **Retrofit**: A type-safe HTTP client for making API requests to the Rick and Morty API.
- **Kotlin Coroutines**: Used for managing background tasks and asynchronous operations in a more efficient and readable way.
- **SQLite**: A local database for storing and querying data locally on the device.
- **Room** : A persistence library that provides an abstraction layer over SQLite to allow easy access to the database while ensuring type safety.
- **Koin** : A dependency injection librairie for kotlin.


