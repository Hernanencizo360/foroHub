ALTER TABLE usuario_perfil
ADD CONSTRAINT fk_usuario_perfil
FOREIGN KEY (usuario_id)
REFERENCES usuario (id)
ON DELETE CASCADE;

