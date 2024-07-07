-- V2__Initial_Data.sql

-- Inserciones para Curso
INSERT INTO curso (nombre, categoria) VALUES ('Spring Boot', 'INFORMATICA');
INSERT INTO curso (nombre, categoria) VALUES ('Desarrollo Web', 'INFORMATICA');
INSERT INTO curso (nombre, categoria) VALUES ('Matemáticas Avanzadas', 'MATEMATICAS');
INSERT INTO curso (nombre, categoria) VALUES ('Estadística Aplicada', 'MATEMATICAS');
INSERT INTO curso (nombre, categoria) VALUES ('Física Cuántica', 'CIENCIAS');
INSERT INTO curso (nombre, categoria) VALUES ('Biología Molecular', 'CIENCIAS');
INSERT INTO curso (nombre, categoria) VALUES ('Pintura Renacentista', 'ARTES');
INSERT INTO curso (nombre, categoria) VALUES ('Música Clásica', 'ARTES');
INSERT INTO curso (nombre, categoria) VALUES ('Literatura Inglesa', 'LENGUAJES');
INSERT INTO curso (nombre, categoria) VALUES ('Español Avanzado', 'LENGUAJES');

-- Inserciones para Perfil
INSERT INTO perfil (nombre) VALUES ('ADMIN');
INSERT INTO perfil (nombre) VALUES ('MODERADOR');
INSERT INTO perfil (nombre) VALUES ('USER');

-- Inserción para Usuario con perfil de ADMIN
INSERT INTO usuario (nombre, correo_electronico, contrasena) VALUES ('admin', 'admin@example.com', 'contrasenaTemporal');

-- Obténer el ID del usuario ADMIN insertado
SET @adminUserId = (SELECT id FROM usuario WHERE correo_electronico = 'admin@example.com');

-- Asignación de perfil ADMIN al usuario
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (@adminUserId, (SELECT id FROM perfil WHERE nombre = 'ADMIN'));






