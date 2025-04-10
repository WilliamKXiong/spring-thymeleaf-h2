# Description 
This project originally started as an example of Spring-Thymeleaf, but involved to include a
game created using kaboom.js, and a chat utilizing sockjs.min.js and stomp.min.js.

## How to Run Locally
This is a Maven Module. This app connects to a postgrel server. You need the correct credentials
in your `local.properties` file. Some features may not work if you are not connected to the postgrel.

## Features
### Basic CRUD (Create, Read, Update, Delete)
Basic CRUD demostrated using lockers. Thymeleaf showcase, using fragments.
### Chat Service
A simple chat service using sockjs and stomp. This does not require a connection to postgrel.
You can test it out by running the app locally and opening up two different browsers, then connect
to the chat. Or if deploy, the chat will work publicly.
### Multiplayer Game Using Kaboom.js
A simple eat fruits games. Demostrates client-to-server communication, as players can see
each other, and can see each others scores. Similar to the Chat Service, this game utilizes websockets
to enable multiplayer.

## ToDo's (2025-04-10)
- Need to track food and hearts on server so players can see others eat food
- Need to make rendering of other players smoother
- Need to adjust canvas to fit on screen along with scoreboard
- Double check other players are placed relatively in same grid as current players