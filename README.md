
# ReciPie Application

## Overview
The ReciPie application is a web-based platform designed to manage and share recipes. It allows users to create, view, and discover recipes contributed by the community. The application provides features for user authentication, recipe management, and browsing recipes.

## Installation and Setup
To set up the ReciPie application, follow these steps:

## Run Locally

### Clone the project

```bash
  git clone https://github.com/Bester43/ReciPie
```

### Database Configuration:

Update the application.yml file with your database URL, username, and password.

```bash
  spring:
  datasource:
    url: "jdbc:postgresql://<DATABASEURL>:5432/postgres"
    username: <DATABASE_USERNAME>
    password: <DATABASE_PASSWORD>
    driverClassName: org.postgresql.Driver
```

### Run the Application:

```bash
  mvn spring-boot:run
```

## Features
### User Authentication
- The application includes pre-defined users with no ability for new user registration.
- Users can log in using the following credentials:
  - **User 1:**
    - Username: john
    - Password: password
  - **User 2:**
    - Username: jane
    - Password: password

### Recipe Management
- Users can create, update, and delete their recipes.
- Each recipe includes details such as title, description, cooking time, and difficulty level.

### Recipe Discovery
- Users can explore a collection of recipes contributed by the community.

## Usage
1. **Login**: Users can log in to the application using their credentials. The login screen prompts users to enter their username and password.
2. **Recipe Management**: After logging in, users can create new recipes by providing details such as title, description, cooking time, and difficulty level. They can also view, or delete their existing recipes.
3. **Recipe Discovery**: Users can explore a collection of recipes contributed by the community on the Discover page.
