# URL Shortener Coding Exercise

## Task



**URL shortener** in **Java and React**

Features:

- Accepts a full URL and return a shortened URL.
- A shortened URL has a randomly generated alias.
- Allows a user to **customise the shortened URL** if they want to (e.g. user provides `my-custom-alias` instead of a random string).
- Persists the shortened URLs across restarts.
- Exposes a **decoupled web frontend** built with a modern framework using React and Bootstrap
- Exposes a **RESTful API** to perform create/read/delete operations on URLs using swagger at: http://localhost:8080/swagger-ui/index.html  
  → Refers to the provided [`openapi.yaml`](./openapi.yaml) for API structure and expected behaviour.
- Includes the ability to **delete a shortened URL** via the API.
- **Tests**.
- Tests were not working and are commented out

## Prerequisites
- Docker to be installed

## To Build and run

- Clone my fork in the Fork the repo using `git clone https://github.com/dkay59/code-exercise-java/`
- cd to the dir `cd code-exercise-java`
- run `docker compose up --build -d` to run and build the software