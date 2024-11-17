-- Crear tabla Categoria
CREATE TABLE IF NOT EXISTS Categoria (
                                         idCategoria SMALLINT PRIMARY KEY,
                                         nombreCategoria VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255)
    );

-- Crear tabla Autor
CREATE TABLE IF NOT EXISTS Autor (
                                     idAutor SMALLINT PRIMARY KEY,
                                     nombre VARCHAR(100) NOT NULL,
    paisOrigen VARCHAR(50)
    );

-- Crear tabla Libro
CREATE TABLE IF NOT EXISTS Libro (
                                     idLibro SMALLINT PRIMARY KEY,
                                     titulo VARCHAR(255) NOT NULL,
    añoPublicacion SMALLINT,
    disponibilidad BOOLEAN DEFAULT TRUE,
    descripcion VARCHAR(500),
    idCategoriaFK SMALLINT,
    idAutorFK SMALLINT,
    CONSTRAINT fk_categoria FOREIGN KEY (idCategoriaFK) REFERENCES Categoria(idCategoria),
    CONSTRAINT fk_autor FOREIGN KEY (idAutorFK) REFERENCES Autor(idAutor)
    );

-- Crear tabla Cliente
CREATE TABLE IF NOT EXISTS Cliente (
                                       idCliente SMALLINT PRIMARY KEY,
                                       nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100),
    telefono VARCHAR(10),
    estadoCuenta BOOLEAN DEFAULT TRUE
    );

-- Crear tabla Prestamo
CREATE TABLE IF NOT EXISTS Prestamo (
                                        idPrestamo SMALLINT PRIMARY KEY,
                                        idClienteFK SMALLINT NOT NULL,
                                        fechaInicioPrestamo DATE NOT NULL,
                                        fechaFinPrestamo DATE,
                                        CONSTRAINT fk_cliente FOREIGN KEY (idClienteFK) REFERENCES Cliente(idCliente)
    );

-- Crear tabla Prestamo_Libro (relación muchos a muchos entre Prestamo y Libro)
CREATE TABLE IF NOT EXISTS Prestamo_Libro (
                                              idPrestamo SMALLINT,
                                              idLibro SMALLINT,
                                              PRIMARY KEY (idPrestamo, idLibro),
    CONSTRAINT fk_prestamo FOREIGN KEY (idPrestamo) REFERENCES Prestamo(idPrestamo),
    CONSTRAINT fk_libro FOREIGN KEY (idLibro) REFERENCES Libro(idLibro)
    );

-- Insertar datos en la tabla Categoria
INSERT INTO Categoria (idCategoria, nombreCategoria, descripcion)
VALUES
    (1, 'Ficción', 'Novelas, cuentos y relatos imaginarios'),
    (2, 'No Ficción', 'Libros de historia, biografía y otros géneros informativos'),
    (3, 'Ciencia', 'Libros de divulgación científica y tecnología');

-- Insertar datos en la tabla Autor
INSERT INTO Autor (idAutor, nombre, paisOrigen)
VALUES
    (1, 'Gabriel García Márquez', 'Colombia'),
    (2, 'George Orwell', 'Reino Unido'),
    (3, 'Marie Curie', 'Polonia'),
    (4, 'Isaac Asimov', 'Rusia'),
    (5, 'J.K. Rowling', 'Reino Unido');

-- Insertar datos en la tabla Libro
INSERT INTO Libro (idLibro, titulo, añoPublicacion, disponibilidad, descripcion, idCategoriaFK, idAutorFK)
VALUES
    (1, 'Cien Años de Soledad', 1967, TRUE, 'Una novela que narra la historia de la familia Buendía', 1, 1),
    (2, '1984', 1949, TRUE, 'Una obra de ciencia ficción distópica', 1, 2),
    (3, 'La Odisea', -800, TRUE, 'Un poema épico de la antigua Grecia', 1, 3),
    (4, 'Breve Historia del Tiempo', 1988, TRUE, 'Explicación de los conceptos fundamentales de la física', 3, 4),
    (5, 'Harry Potter y la Piedra Filosofal', 1997, TRUE, 'Una historia de magia y aventura para jóvenes y adultos', 1, 5),
    (6, 'Sapiens: De Animales a Dioses', 2011, TRUE, 'Historia de la humanidad desde sus orígenes hasta el presente', 2, 2);

-- Insertar datos en la tabla Cliente
INSERT INTO Cliente (idCliente, nombre, correo, telefono, estadoCuenta)
VALUES
    (1, 'Juan Pérez', 'juan.perez@email.com', '123456789', TRUE),
    (2, 'Ana Gómez', 'ana.gomez@email.com', '987654321', TRUE),
    (3, 'Luis Martínez', 'luis.martinez@email.com', '112233445', FALSE);

-- Insertar datos en la tabla Prestamo
INSERT INTO Prestamo (idPrestamo, idClienteFK, fechaInicioPrestamo, fechaFinPrestamo)
VALUES
    (1, 1, '2024-10-01', '2024-10-15'),
    (2, 2, '2024-10-05', '2024-10-20'),
    (3, 3, '2024-11-01', NULL);
