# Android Assignment MoviesMVP
Android app that displays movies and shows the details

## Features
* List of movies from https://www.themoviedb.org/ divided by categories: Latest, Popular and Upcoming 
* Movie Poster images cached to improve user experience
* Movies persisted with room
* Material components and styles
* Presenter unit tests

## Project Structure
The code base is organized in 3 different packages
- domain: Contains models
- infrastructure: Contains classes and interfaces for local and remote repositories
- presentation: Contains fragments, activities, presenters and interactors

## Architecture
### MVP
The app is composed of the views:
- `MainActivity`
- `MovieCategoriesFragment`
- `MovieDetailFragment`
The MovieCategoriesFragment has a presenter and interactor.

### Repositories
The interactor requires a remote repository to get information. The `Repository` interface can be implemented to get data from the internet or from the device. 
In this project, Retrofit is used as an implementation to get data from a remote movie api.

### Dependency injection
The dependencies required for presenter and interactor are managed by Hilt. 
Hilt configuration modules are in the `di` package.

### Image caching
Glide is used to cache movie poster images

## Third party libraries
- Retrofit for internet connectivity
- Room for persistency
- Glide for image caching
- Hilt for dependency injection
