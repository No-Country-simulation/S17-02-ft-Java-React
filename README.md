
# Proyecto HeyDoc!

## Descripción

[Nombre del Proyecto] es una aplicación web moderna que utiliza **Spring Boot** para el backend, **ReactJS** para el frontend, y **PostgreSQL** para la base de datos. La aplicación está diseñada para ofrecer una experiencia de usuario intuitiva y eficiente, utilizando **Bootstrap** para el diseño responsivo y **Figma** para los wireframes. El equipo usa **Postman** para pruebas y documentación de la API.

## Tecnologías

- **Backend**: Spring Boot, Java 21
- **Base de Datos**: PostgreSQL
- **Frontend**: ReactJS, Bootstrap
- **Diseño**: Figma
- **Testing**: Postman
- **Contenedores**: Docker

## Estructura del Equipo

- **Backends**: 4
- **Frontend**: 4
- **UX/UI Designer**: 1
- **QA**: 1

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
