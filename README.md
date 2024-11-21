# REST API Biblioteca UD

## Requisitos

- Java 21 o superior
- Gradle 6.0 o superior
- PostgreSQL 8.x o superior

## Configuración del Proyecto

- Clonar el repositorio:

```sh
git clone https://github.com/leiderY2K/SGB-backend-.git
```

- Navegar al directorio del proyecto:

```sh
cd tu_proyecto
```

- Construir el proyecto:

```sh
./gradlew build
```

## Configuración de la Base de Datos
Es necesario ejecutar el script SQL create-database.sql que se encuentra en src/main/resources/persistence para poder montar la base de datos de manera local. Sigue estos pasos:

1. Conéctate a tu instancia de PostgreSQL.

2. Ejecuta el script SQL:

```sh
psql -U postgres -f src/main/resources/persistence/create-database.sql
```
Si todo sale bien, esto creará la estructura necesaria de la base de datos para que el proyecto funcione correctamente.

## Ejecución del Proyecto
Para ejecutar la aplicación, usa el siguiente comando:

```sh
./gradlew bootRun
```

## Endpoints
La descripción de los Endpoints está pendiente.