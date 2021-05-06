create table `usuario`(
    `id`   INTEGER PRIMARY KEY AUTO_INCREMENT,
    `nome`         VARCHAR(50)  NOT NULL,
    `email`        CHAR(80)     NOT NULL,
    `senha`        CHAR(64)     NOT NULL,
    `descricao`    VARCHAR(200) NOT NULL,
    `facebook`     VARCHAR(80),
    `linkedin`     VARCHAR(80),
    `token`        VARCHAR(100),
    `oauth`        VARCHAR(80) ,
    `status`       INTEGER NOT NULL,
    `data_criacao` DATE NOT NULL
);

#INSERT INTO usuario (nome,email,senha,celular,foto,ativo) VALUES ('Administrador','admin@email.com','$2a$10$ORL7ZUcn9XuZY9L6qUxzxufXj4eqNTt2IasaJDQySp4XxAsONyZDa','481234124235','uploads/',1);
#INSERT INTO usuario (nome,email,senha,celular,foto,ativo) VALUES ('Joao','joao@email.com','$2a$10$3/G88lXwKC5RFnPyfPtukOJ2hjv5VpYrupFSA09.sxJCwNLOJIlqS','489876124235','uploads/',1);
#INSERT INTO usuario (nome,email,senha,celular,foto,ativo) VALUES ('Maria','maria@email.com','$2a$10$3/G88lXwKC5RFnPyfPtukOJ2hjv5VpYrupFSA09.sxJCwNLOJIlqS','484534124235','uploads/',0);
#INSERT INTO usuario (nome,email,senha,celular,foto,ativo) VALUES ('Rose','rose@email.com','$2a$10$3/G88lXwKC5RFnPyfPtukOJ2hjv5VpYrupFSA09.sxJCwNLOJIlqS','481028124235','uploads/',0);
