#💬 Java Multi-Threaded Chat Room

A multi-threaded client-server chat application built with Java Sockets and MySQL.
The server handles multiple clients concurrently and stores chat messages in a database.

#🚀 Features

Multi-threaded server (each client handled in a separate thread)

Real-time message broadcasting

Persistent message storage using MySQL

Automatic loading of previous chat history for new users

Basic Ping measurement

Multiple clients can connect simultaneously

#🏗 Architecture
##Server Side

ServerSocket listens on port 6666

Each new client connection is assigned a CommunicationHandler thread

Messages are:

Saved into MySQL database

Broadcast to other connected clients

##Client Side

Each client:

Connects to server via Socket

Uses:
SendMessages thread (to send messages)

ReceiveMessage thread (to receive messages)
#🛠 Technologies Used

Java (Core Java, Multithreading)

Java Sockets (TCP)

MySQL

JDBC

#📂Project Structure

```
chatroom/
│
├── Server/
│   └── Server.java
│
├── DataBase/
│   └── DataBase.java
│
├── streams/
│   ├── SendMessages.java
│   └── ReceiveMessage.java
│
└── allClients/
    └── client.java
```

