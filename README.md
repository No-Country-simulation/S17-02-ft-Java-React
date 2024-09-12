
# Proyecto HeyDoc!

## Descripción

HeyDoc es una aplicación web moderna que utiliza **Spring Boot** para el backend, **ReactJS** para el frontend, y **PostgreSQL** para la base de datos. La aplicación está diseñada para ofrecer una experiencia de usuario intuitiva y eficiente, utilizando **Bootstrap** para el diseño responsivo y **Figma** para los wireframes. El equipo usa **Postman** para pruebas y documentación de la API.

## Tecnologías

- **Backend**: Spring Boot, Java 21
- **Base de Datos**: PostgreSQL
- **Frontend**: ReactJS, Bootstrap
- **Diseño**: Figma
- **Testing**: Postman
- **Contenedores**: Docker

## Estructura del Equipo

## Backends:
#### Tech Stack
<div>
<img src="https://www.vectorlogo.zone/logos/java/java-icon.svg" width="40" height="40"/>
<img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" width="40" height="40"/>
</div>

#### 🧑‍💻 Developers:

| <img src="https://avatars.githubusercontent.com/u/30301847?v=4" width=50>| <img src="https://avatars.githubusercontent.com/u/125727012?v=4" width=50>|
|:-:|:-:|
| **Anderson Cusma**| **Catriel Escobar**|
| <a href="https://github.com/anderson2093"><img src="https://img.shields.io/badge/github-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white"/></a><a href="https://www.linkedin.com/in/anderson-cusma-vasquez/"><img src="https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white"/></a> | <a href="https://github.com/Catriel-Escobar"><img src="https://img.shields.io/badge/github-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white"/></a> <a href="https://www.linkedin.com/in/catrielescobar/"><img src="https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white"/></a> |


| <img src="https://avatars.githubusercontent.com/u/16294803?v=4" width=50>| 
|:-:|
| **Rogelio Olarte**|
| <a href="https://github.com/rogelioolarte"><img src="https://img.shields.io/badge/github-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white"/></a><a href="https://www.linkedin.com/in/rogelio-olarte"><img src="https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white"/></a> 

## Frontend

#### Tech Stack

![React](https://img.shields.io/badge/React-61dbfb?style=for-the-badge&logo=React&logoColor=black)
![Visual Studio Code](https://img.shields.io/badge/Visual_Studio_Code-22A7F2?style=for-the-badge&logo=Visual%20studio&logoColor=white)

## UX/UI Designer

#### Tech Stack

![Figma](https://img.shields.io/badge/Figma-00000?style=for-the-badge&logo=Figma&logoColor=black)

#### 🧑‍💻 Designers:

| <img src="https://media.licdn.com/dms/image/v2/D4D03AQGCU-3QKanPFg/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1699392640828?e=1731542400&v=beta&t=YDmaW0ITeL6MQ61tOplVJdHPT9Vd6tSyGEWDHoA9dII" width=50>| 
|:-:
| **Nicolás García Tortosa**
| <a href="https://www.behance.net/nicotortosa"><img src="https://img.shields.io/badge/behance-%23121011.svg?&style=for-the-badge&logo=behance&logoColor=white"/></a> <a href="https://www.linkedin.com/in/nicol%C3%A1s-garc%C3%ADa-tortosa-28b392190/"><img src="https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white"/></a>
## QA

## Instalación y Despliegue con Docker

### Requisitos

- **Docker** y **Docker Compose**

### Clonar el Repositorio

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/tu_repositorio.git
   cd tu_repositorio
   ```

### Construcción y Ejecución

1. **Construir y ejecutar los contenedores**:
   - Si tienes un archivo `docker-compose.yml` en tu proyecto, puedes usar Docker Compose para construir y ejecutar los contenedores:
     ```bash
     docker-compose up --build
     ```

2. **Construir imágenes manualmente**:
   - **Backend**:
     ```bash
     docker build -t nombre_usuario/backend .
     ```
   - **Frontend**:
     ```bash
     docker build -t nombre_usuario/frontend ./frontend
     ```

3. **Ejecutar los contenedores**:
   - **Backend**:
     ```bash
     docker run -d -p 8080:8080 --name backend nombre_usuario/backend
     ```
   - **Frontend**:
     ```bash
     docker run -d -p 3000:3000 --name frontend nombre_usuario/frontend
     ```

4. **Base de Datos**:
   - La base de datos PostgreSQL puede ser configurada como un contenedor o en un servidor externo. Asegúrate de que los contenedores del backend se conecten a la base de datos correcta.

### Acceso a la Aplicación

1. **Backend**: `http://localhost:8080`
2. **Frontend**: `http://localhost:3000`

### Configuración de Docker

- Los detalles de la configuración de Docker, como los `Dockerfile` y `docker-compose.yml`, están en el repositorio. Asegúrate de revisar estos archivos para ajustar la configuración según tus necesidades.

## Wireframes

Los wireframes y diseños están disponibles en [Figma](https://www.figma.com/file/tu-enlace-a-figma).

## Pruebas

- **Backend**: Usa [Postman](https://www.postman.com/) para probar los endpoints de la API.
- **Frontend**: Realiza pruebas manuales y automatizadas para garantizar la calidad y el rendimiento.

## Contribución

Si deseas contribuir al proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad o corrección de errores.
3. Realiza tus cambios y haz commit.
4. Envía un pull request con una descripción detallada de tus cambios.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Para consultas o soporte, contacta al equipo a través de [tu_email@dominio.com].

## Despliegue en Docker Hub

Las imágenes Docker están disponibles en [Docker Hub](https://hub.docker.com/repository/docker/nombre_usuario/backend) y [Docker Hub](https://hub.docker.com/repository/docker/nombre_usuario/frontend).

---

Este README incluye información sobre cómo construir y ejecutar los contenedores Docker, además de proporcionar enlaces a Docker Hub donde se pueden encontrar las imágenes del proyecto. Asegúrate de actualizar los enlaces y nombres según los detalles específicos de tu proyecto.
