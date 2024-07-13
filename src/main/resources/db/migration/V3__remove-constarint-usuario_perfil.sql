ALTER TABLE usuarios_perfiles
ADD CONSTRAINT fk_usuarios_perfiles
FOREIGN KEY (usuario_id)
REFERENCES usuarios (id)
ON DELETE CASCADE;
