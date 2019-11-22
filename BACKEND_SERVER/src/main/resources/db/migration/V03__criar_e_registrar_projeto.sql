create table `projeto`(
    `id`      INTEGER PRIMARY KEY AUTO_INCREMENT,
    `nome`            VARCHAR(80)  NOT NULL,
    `objetivo`        VARCHAR(350) NOT NULL,
    `visivel`         INTEGER default 1,
    `concluido`       INTEGER default 0,
    `data_criacao`    DATETIME NOT NULL,
    `data_conclusao`  DATETIME NOT NULL
);
