## Introduction

The project is based on the Spotify multimedia platform and seeks to simulate some functionalities that can be done within the original app, for example, upload a new song, create a playlist and play a song.


## About development

At the moment the development is only back-end and to communicate with the system a REST API is used (documentada con Sagger).As programming language use Java 11 together with Spring boot 2.5 and MySQL as database.
Throughout the project I apply knowledge about design patterns (such as DTO, composite, adapter and singleton), mapping java classes to database tables, http protocol, collection management, SOLID principles.


## Funcionalidades

* Sign up, log in, log out
* Create playlist
* Add song to playlist
* Play song or playlist
* Manage play queue (add songs/playlists or shuffle)
* Register artist
* Upload song


## For running

For the system to work you must:
* Have a jdk 11, consult java documentation on how to install
* Specify the connection data with the database in the properties file, for this I recommend consulting the documentation of the MySQL drive or using the following site https://www.baeldung.com/java-connect-mysql. In the project properties file there is already a possible configuration, it only remains to create the schema in the DB


## API REST

The API is currently still under development, therefore not all features are implemented (login, play, pause).
To see the documentation of the endpoints, run the system and enter http://localhost:8080/swagger-ui.html
