create table `iteracao`(
    `id`  INTEGER PRIMARY KEY AUTO_INCREMENT,
    `nome`         VARCHAR(80)  NOT NULL,
    `concluido`    INTEGER default 0,
    `id_projeto`    INTEGER,
    FOREIGN KEY (`id_projeto`)  REFERENCES `projeto`(`id`)
);