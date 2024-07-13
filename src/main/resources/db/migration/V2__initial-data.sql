-- Inserciones para Curso
INSERT INTO cursos (nombre, categoria) VALUES ('Spring Boot', 'INFORMATICA');
INSERT INTO cursos (nombre, categoria) VALUES ('Desarrollo Web', 'INFORMATICA');
INSERT INTO cursos (nombre, categoria) VALUES ('Matemáticas Avanzadas', 'MATEMATICAS');
INSERT INTO cursos (nombre, categoria) VALUES ('Estadística Aplicada', 'MATEMATICAS');
INSERT INTO cursos (nombre, categoria) VALUES ('Física Cuántica', 'CIENCIAS');
INSERT INTO cursos (nombre, categoria) VALUES ('Biología Molecular', 'CIENCIAS');
INSERT INTO cursos (nombre, categoria) VALUES ('Pintura Renacentista', 'ARTES');
INSERT INTO cursos (nombre, categoria) VALUES ('Música Clásica', 'ARTES');
INSERT INTO cursos (nombre, categoria) VALUES ('Literatura Inglesa', 'LENGUAJES');
INSERT INTO cursos (nombre, categoria) VALUES ('Español Avanzado', 'LENGUAJES');

-- Inserciones para Perfil
INSERT INTO perfiles (nombre) VALUES ('ADMIN');
INSERT INTO perfiles (nombre) VALUES ('MODERADOR');
INSERT INTO perfiles (nombre) VALUES ('USUARIO');

-- Inserción para Usuario con perfil de ADMIN
INSERT INTO usuarios (nombre, correo_electronico, contrasena) VALUES ('admin', 'admin@example.com', '$2a$12$fb97.JSDlWCJMLYnF7M9f.VFpXdrvfbLLjEDyDCIOCkcMkvyOTfxq');

-- Obténer el ID del usuario ADMIN insertado
SET @adminUserId = (SELECT id FROM usuarios WHERE correo_electronico = 'admin@example.com');

-- Asignación de perfil ADMIN al usuario
INSERT INTO usuarios_perfiles (usuario_id, perfil_id) VALUES (@adminUserId, (SELECT id FROM perfiles WHERE nombre = 'ADMIN'));