# Pet Finder Application

## Overview

The **Pet Finder** application is designed to facilitate the adoption of pets and help find homeless animals in need of homes. This platform allows users to connect with animal shelters, manage their accounts, and interact with various animal listings.

## Features

- **User and Shelter Manager Accounts**: Users can sign up and log in as regular users, while shelter managers have predefined accounts for managing animal listings.
- **Animal Listings**: Users can view, search, and post animals for adoption.
- **Favorites**: Users can add animals to their favorites for easy access.
- **Breed Information**: Users can access information about different animal breeds.
- **Database Connection**: The application uses repository classes for efficient database management.
- **Enumerations for Colors**: Color selections are managed using enumerations for consistency.

## Application Structure

### Views

1. **LoginView**: Allows users to log in with their username and password. New users can create an account via the "Sign Up" button.
2. **SignUpView**: Users can create an account by providing personal information (name, date of birth, gender, city, county, phone number, email, and username). Usernames must be unique, and emails must follow the format `@gmail.com` or `@yahoo.com`.
3. **HomePageView (User)**: After logging in, users are redirected here, where they can view their account, post an animal, search for animals, or learn about animal breeds.
4. **AccountView**: Users can view and edit their profile, including personal information and a list of animals they have added. They can delete animals from their list.
5. **InfoBreedView**: Users can select a breed category and view detailed information about that breed.
6. **AddAnimalView**: Users can add an animal by providing necessary information such as category, age, breed, gender, color, description, location, and a photo.
7. **SearchAnimalView**: Users can search for animals based on specific criteria like category, breed, and city.
8. **HomePageView (Manager)**: Managers can add new breeds from this view.
9. **AddNewBreedView (Manager)** : Managers can create a new breed by providing a breed name and description and view existing breeds.

## Database Information 
The application uses **PostgreSQL** as its database management system. The database is designed to handle many-to-many relationships.

## Authentication Details

### Manager Authentication
- **Username**: HopeShelter
- **Password**: hope

### Example User Account
- **Username**: GabiPirvulescu
- **Password**: gabi

### Search Criteria Example
To search for a pet, you can use the following criteria:
- **Category**: Cat
- **Breed**: Siamese
- **Location**: Botosani
