create table `requisito`(
    `id`  INTEGER PRIMARY KEY AUTO_INCREMENT,
    `codigo`        VARCHAR(10)  NOT NULL,
    `nome`          VARCHAR(80)  NOT NULL,
    `prioridade`    INTEGER(10)  NOT NULL,
    `concluido`     INTEGER(1)   NOT NULL default 0,
    `responsaveis`  VARCHAR(200),
    `id_projeto`    INTEGER,
    `id_iteracao`   INTEGER,
    FOREIGN KEY (`id_projeto`)    REFERENCES `projeto`(`id`),
    FOREIGN KEY (`id_iteracao`)   REFERENCES `iteracao`(`id`)
);