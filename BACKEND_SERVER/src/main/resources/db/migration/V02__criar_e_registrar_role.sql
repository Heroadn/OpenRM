CREATE TABLE role (
  id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(255) DEFAULT NULL,
  nome VARCHAR(255) DEFAULT NULL
);

CREATE TABLE usuario_role (
  id_usuario INTEGER NOT NULL,
  id_role INTEGER NOT NULL,
 FOREIGN KEY (id_usuario) REFERENCES usuario(id),
 FOREIGN KEY (id_role)    REFERENCES role (id)
);


INSERT INTO role (id, nome, descricao) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO role (id, nome, descricao) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');