create table `regra_de_negocio`(
    id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    texto        VARCHAR(80) NOT NULL,
    concluido    INTEGER NOT NULL,
    id_requisito INTEGER  NOT NULL,
    FOREIGN KEY (id_requisito )   REFERENCES requisito(id)
);
