# Proyecto Fin de Master Spring Framework Y Spring Boot 2.7.2

## Descripción

El proyecto "Spring Biblioteca JPA" es un sitio web para la gestión del servicio de préstamo de una biblioteca. Este proyecto se ha desarrollado utilizando el IDE Spring Tool Suite 4 y se apoya en varias dependencias clave de Spring, incluidas:

- Spring Web
- Spring Boot Dev Tools
- Thymeleaf
- Validation
- H2 Database
- Spring Data JPA

## Características

- **Gestión de Socios y Libros:** Permite la creación, actualización y eliminación de socios y libros en la biblioteca.
- **Préstamos:** Gestiona el préstamo de libros a los socios, asegurando que un libro solo pueda ser prestado a un socio a la vez.
- **Interfaz de Usuario:** Utiliza Bootstrap para una maquetación clara y responsive, con navegación intuitiva a través de diferentes secciones como inicio, socios, catálogo, y préstamos.
- **Base de Datos en Memoria:** Emplea H2 Database para el almacenamiento de datos en modo de ejecución en memoria, facilitando la configuración y el despliegue.

## Configuración del Proyecto

### Requisitos

- JDK 11 o superior.
- Spring Tool Suite 4 o cualquier IDE compatible con proyectos Spring.

### Instalación

1. Clona este repositorio en tu máquina local usando:
```
git clone https://github.com/amaliasgdev/spring-biblioteca-jpa.git
```

2. Abre el proyecto en Spring Tool Suite 4 o tu IDE de elección.

### Base de Datos

Para acceder a la consola de la base de datos H2:

- Modifica el archivo `src/main/resources/application.properties` para incluir las siguientes propiedades, asegurando que el nombre de la base de datos sea `biblioteca` y se pueda acceder con el usuario `admin` y la contraseña `password`:
```
spring.datasource.url=jdbc:h2:mem:biblioteca
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=password
spring.h2.console.enabled=true
spring.h2.console.path=/h2
```


### Ejecución

Para ejecutar el proyecto:

1. Navega al directorio raíz del proyecto.
2. Ejecuta el siguiente comando:
```
./mvnw spring-boot:run
```
3. Abre un navegador y visita `http://localhost:8080` para acceder a la aplicación.

## Uso

- **Inicio:** Muestra las novedades, incluyendo los últimos libros adquiridos por la biblioteca.
- **Socios:** Permite la gestión de socios, incluyendo la creación, edición y eliminación de estos.
- **Catálogo:** Gestiona el catálogo de libros de la biblioteca, permitiendo su creación, edición, préstamo y expurgación.
- **Préstamos:** Muestra los préstamos actuales y permite gestionar nuevos préstamos y devoluciones.

## Contribuir

Si deseas contribuir a este proyecto, por favor haz un fork del repositorio y envía un pull request con tus cambios.
