## Introducción

El proyecto está basado en la plataforma multimedia Spotify y busca simular algunas funcionalidades que se pueden hacer dentro de la app original, por ejemplo, subir una canción nueva, crear una playlist y reproducir una canción.


## Sobre el desarrollo

El desarrollo es 100% back-end y para comunicarse con el sistema se utiliza una API REST (documentada con Sagger). Como lenguaje de programación utilice Java 11 en conjunto con Spring boot 2.5 y como base de datos use MySQL. 
A lo largo del proyecto se aplican conocimientos sobre patrones de diseño (como DTO, composite, adapter y singleton), mapeo de clases java a tablas de la BD, protocolo http, manejo de colecciones, principios SOLID.


## Funcionalidades

* Sign up, log in, log out en un dispositivo
* Crear playlists
* Agregar canciones a la playlist
* Reproducir una canción o una playlist completa
* Manejar su cola de reproducción (agregar canciones/playlists o mezclarla)
* Registrar artistas
* Subir una cancion de un artista


## Para probar

Para que el sistema funcione se debe:
Contar con una jdk 11, consultar documentación de java sobre como instalar
Especificar los datos de conexión con la base de datos en el archivo properties, para ello recomiendo consultar la documentacion del drive de MySQL o guairse con el siguiente sitio https://www.baeldung.com/java-connect-mysql


## API REST

Actualmente la API sigue en desarrollo, por lo tanto no todas las funcionalidades están implementadas (login, play, pause).
Para ver la documentación de los endpoints correr el sistema y entrar en http://localhost:8080/swagger-ui.html
