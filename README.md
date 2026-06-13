# Sistema de Gestión de Solicitudes
Este proyecto es una aplicación de consola desarrollada en Java para la gestión de solicitudes. Implementa una arquitectura en capas para mantener el código organizado, escalable y fácil de mantener.
#Características Principales
El sistema cuenta con un sistema de roles para diferenciar los permisos de los usuarios:
# Rol: SOLICITANTE
Los usuarios con este rol tienen acceso a las siguientes funcionalidades básicas:
- Registrarse en el sistema.
- Crear nuevas solicitudes indicando el tipo y descripción.
# Rol: FUNCIONARIO
Los funcionarios tienen acceso administrativo al sistema y pueden realizar múltiples operaciones:
- Registrar nuevos usuarios.
- Crear y definir nuevos tipos de solicitudes (nombre, descripción, tiempo estimado).
- Crear solicitudes en nombre de otros usuarios.
- Cambiar el estado de las solicitudes (ej: `CREADA`, `EN_REVISION`, `APROBADA`, `RECHAZADA`, `RESUELTA`).
- Buscar y filtrar solicitudes por su estado actual.
- Generar reportes basados en el tipo de solicitud.
# Arquitectura del Proyecto
El proyecto está diseñado utilizando una **Arquitectura en Capas** estricta para separar responsabilidades:
- **Capa de Presentación (`capa_presentacion`):** Contiene la clase `Main` y la interfaz de consola interactiva. Se encarga de capturar la entrada del usuario y mostrar los resultados.
- **Capa de Negocio (`capa_negocio`):** Contiene las entidades del dominio (como `Usuario`, `Solicitud`, `TipoSolicitud`) y los servicios que aplican la lógica de negocio. Utiliza un patrón similar a CQRS, separando las operaciones de escritura (Command Services) de las de lectura (Query Services).
- **Capa de Acceso a Datos (`capa_acceso_datos`):** Contiene las interfaces de los repositorios y sus implementaciones concretas para la persistencia de datos (ej: `UsuarioRepositoryPotgrets`), diseñadas para integrarse con una base de datos PostgreSQL.
# Tecnologías y Patrones de Diseño
- Lenguaje: Java
- Base de Datos: PostgreSQL (integrado mediante la capa de acceso a datos)
- Patrón Builder: Implementado en las entidades del dominio para facilitar la creación de objetos complejos (ej: `new Usuario.Builder()...build()`).
- Patrón Repository: Utilizado para abstraer el acceso a los datos y permitir cambiar la base de datos fácilmente en el futuro.
- Separación de Comandos y Consultas (CQRS): Los servicios de negocio están divididos en `ComandService` (para modificaciones) y `QueryServise` (para lecturas).
# Cómo ejecutar el proyecto
1. Clona este repositorio en tu máquina local:
escribe en la terminal dentro de tu carpeta lo siguiente:
git clone https://github.com/Jaider-Rendon/Proyecto_Final.git
Asegúrate de tener instalado Java JDK.
Configura tu base de datos PostgreSQL con las tablas necesarias (Usuarios, Tipos de Solicitud, Solicitudes)
# Script para pegar en tu Bases de Datos 
-- 1. Crear tabla Usuario
CREATE TABLE usuario (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    rol VARCHAR(50) NOT NULL
);
-- 2. Crear tabla TipoSolicitud
CREATE TABLE tipoSolicitud (
    idTipoSolicitud SERIAL PRIMARY KEY,
    nombretipo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    tiempoEstimadoDias INTEGER NOT NULL
);

-- 3. Crear tabla Solicitud
CREATE TABLE solicitud (
    idsolicitud SERIAL PRIMARY KEY,
    idUsuario INTEGER NOT NULL,
    idTipoSolicitud INTEGER NOT NULL,
    descripcion TEXT,
    fechaCreacion VARCHAR(50), 
    estado VARCHAR(50),
    FOREIGN KEY (idUsuario) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (idTipoSolicitud) REFERENCES tipoSolicitud(idTipoSolicitud) ON DELETE CASCADE
);

-- 4. Crear tabla Notificaciones 
CREATE TABLE notificaciones (
    idnotificacion SERIAL PRIMARY KEY,
    idsolicitud INTEGER NOT NULL,
    mensaje TEXT,
    fecha VARCHAR(50),
    estadoSolicitud VARCHAR(50),
    FOREIGN KEY (idsolicitud) REFERENCES solicitud(idsolicitud) ON DELETE CASCADE
);

Compila y ejecuta la clase capa_presentacion.Main desde tu IDE favorito (como VS Code o Eclipse ) o desde la terminal.
Al iniciar, el sistema te pedirá tu ID. Si no estás registrado, el sistema te guiará para crear un nuevo usuario.
# Autores
-Jaider Rendon
-Juan Felipe Cely
-Juan Diego Bernal
-Juan Esteban Cifuentes
