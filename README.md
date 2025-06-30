<h1 align="center">
  NutriVision: Food Barcode Scanner
</h1>
<p align="center">
  Mobile Development Final Project | Group 5
</p>
<p align="center">
  <a href="http://developer.android.com/index.html"><img alt="Platform" src="https://img.shields.io/badge/platform-Android-green.svg"></a>
  <img alt="api" src="https://img.shields.io/badge/API-30%2B-green?logo=android"/>
  <a href="https://developer.android.com/studio/releases/gradle-plugin"><img alt="Gradle" src="https://img.shields.io/badge/gradle-8.11.1-green.svg"></a>
  <a href="https://github.com/riyhs/NutriVision/"><img alt="Star" src="https://img.shields.io/github/stars/riyhs/NutriVision"></a>
</p>

## Table of Contents
- [Introduction](#introduction)
- [Contributors](#contributors)
- [Features](#features)
- [Schreenshots](#schreenshots)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Architecture](#architecture)
- [Acknowledgments](#acknowledgments)


## Introduction
NutriVision is a modern Android application designed to help users make healthier and more informed food choices. By simply scanning a product's barcode with their phone's camera, users get instant access to detailed nutritional information, powered by the comprehensive [Open Food Facts](https://world.openfoodfacts.org/) database.

This app is perfect for anyone conscious about their daily nutritional intake, from athletes and individuals with specific dietary needs (like diabetes) to the general public pursuing a healthier lifestyle.


## Contributors
| No. | Name                        | NIM      | GitHub                                         |
| --- | --------------------------- | -------- | ---------------------------------------------- |
| 1   | Riyaldi Hasan Setiawan      | L0122141 | [GitHub](https://github.com/riyhs)             |
| 2   | Rifqi Makarim               | L0123122 | [GitHub](https://github.com/RifqiMakarim)      |
| 3   | Rasendria Acyuta Aji Candra | L0123116 | [GitHub](https://github.com/rasengasukacoding) |

## Features

  - **Barcode Scanner**: Instantly scan food product barcodes using the device's camera to fetch product data.
  - **Detailed Product Information**: View comprehensive data including product name, image, brand, ingredients, and a full nutritional breakdown (energy, sugar, fat, protein, salt, etc.).
  - **Scan History**: Keeps a list of previously scanned products, allowing users to quickly access information without needing to scan again.
  - **Light & Dark Mode**: Seamlessly switches between light and dark themes based on the system settings to ensure user comfort.
  - **BMI Calculator**: An integrated tool to calculate Body Mass Index (BMI) and receive personalized nutrition tips based on the result.
  - **User Authentication & Cloud Sync**:
      - **Sign Up**: Create a personal account using Firebase Authentication.
      - **Login**: Access your account to load and manage your user data.
      - **Profile Management**: User data (like BMI details and profile picture URL) is stored securely in Firebase Firestore and Cloudinary.


## Screenshots 

| Home                            |
| ------------------------------- |
| ![](screenshots/1.png?raw=true) |

| Dashboard                       |
| ------------------------------- |
| ![](screenshots/2.png?raw=true) |

| Photobooth                      |
| ------------------------------- |
| ![](screenshots/3.png?raw=true) |


## Tech Stack & Architecture

This project is built with a modern Android development tech stack, following the principles of Clean Architecture and the MVVM (Model-View-ViewModel) pattern.

  - **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
  - **Asynchronous**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-guide.html)
  - **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
  - **Networking**: [Retrofit 2](https://square.github.io/retrofit/)
  - **Local Storage**: [Room](https://developer.android.com/training/data-storage/room)
  - **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
  - **Navigation**: [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
  - **Backend Services**:
      - [Firebase Authentication](https://firebase.google.com/docs/auth) for user sign-up and login.
      - [Firebase Firestore](https://firebase.google.com/docs/firestore) for storing user profile data.
      - [Cloudinary](https://cloudinary.com/) for hosting user-uploaded profile images.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

  - Android Studio (latest version recommended)
  - A Google Account for Firebase
  - A Cloudinary Account

### Installation & Setup

1.  **Clone the repository**

    ```sh
    git clone https://github.com/riyhs/NutriVision.git
    ```

2.  **Set up Firebase**

      - Go to the [Firebase Console](https://console.firebase.google.com/).
      - Click **Add Project** and follow the on-screen instructions to create a new project.
      - Inside your new project, click **Add app** and select the Android icon (▶).
      - Enter the application's package name: `com.nutrivision.app`.
      - Download the `google-services.json` file.
      - Place the downloaded `google-services.json` file in the **`app/`** directory of the project.
      - In the Firebase Console, navigate to the **Authentication** section and enable the **Email/Password** sign-in method.
      - Navigate to the **Firestore Database** section and create a new database. Start in **test mode** for easy setup (you can change the security rules later).

3.  **Set up Cloudinary**

      - Log in to your [Cloudinary](https://cloudinary.com/) account.
      - From your Dashboard, find your **Cloud Name**, **API Key**, and **API Secret**.
      - Create a file named `local.properties` in the root directory of the project (the same level as `build.gradle.kts`).
      - Add your Cloudinary credentials to the `local.properties` file:
        ```properties
        CLOUDINARY_CLOUD_NAME="your_cloud_name"
        CLOUDINARY_API_KEY="your_api_key"
        CLOUDINARY_API_SECRET="your_api_secret"
        ```

4.  **Open and Run in Android Studio**

      - Open the cloned project in Android Studio.
      - Let Android Studio sync the Gradle files.
      - Run the app on an emulator or a physical device.

## Architecture Overview

The application's architecture is divided into three main layers:

### 1\. Domain Layer

This is the core of the application. It contains the business logic, data models (`Product`, `User`), and repository interfaces (`ScanRepository`, `AuthRepository`). This layer is independent of any framework specifics.

### 2\. Data Layer

This layer is responsible for providing data to the domain layer. It contains implementations of the repository interfaces and manages remote (Retrofit `ApiService`, Firebase), cloud (Cloudinary), and local (`Room` DAO) data sources.

### 3\. UI (Presentation) Layer

This layer is responsible for displaying the application's UI. It's built with Jetpack Compose and uses ViewModels (`ScanViewModel`, `AuthViewModel`) to manage UI state and interact with the domain layer.

## Acknowledgments

  - This project heavily relies on the data provided by the [Open Food Facts](https://world.openfoodfacts.org/) API. A huge thank you to their community for maintaining this open database.
