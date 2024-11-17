#!/bin/bash
# Esperar a que PostgreSQL esté listo
until pg_isready -h "localhost" -U "$POSTGRES_USER"; do
  echo "Esperando a que PostgreSQL esté listo..."
  sleep 2
done

# Crear la base de datos (esto ya debería ser manejado por POSTGRES_DB)
psql -U "$POSTGRES_USER" -c "CREATE DATABASE biblioteca;"
