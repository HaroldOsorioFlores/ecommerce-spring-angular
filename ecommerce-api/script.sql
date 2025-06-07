CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0
);


CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido TEXT,
    email VARCHAR(255) NOT NULL
);

INSERT INTO productos (nombre, descripcion, precio, stock) VALUES
('Laptop', 'Portátil de alta gama', 999.99, 10),
('Smartphone', 'Teléfono inteligente con cámara de 108MP', 699.50, 25),
('Tablet', 'Tablet de 10 pulgadas con Android', 299.99, 15),
('Monitor', 'Monitor LED de 24 pulgadas Full HD', 199.90, 12),
('Teclado', 'Teclado mecánico retroiluminado', 89.95, 40),
('Mouse', 'Mouse inalámbrico ergonómico', 49.99, 30),
('Impresora', 'Impresora multifunción a color', 159.00, 8),
('Auriculares', 'Auriculares inalámbricos con cancelación de ruido', 129.99, 20),
('Webcam', 'Webcam HD para videollamadas', 59.90, 18),
('Disco Duro Externo', 'Disco duro externo de 1TB', 79.99, 22);


INSERT INTO clientes (nombre, apellido, email)
VALUES ('PruebaNombre', 'PruebaApellido', 'prueba@gmail.com');

