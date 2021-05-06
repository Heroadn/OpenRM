create table `midia_usuario`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    data_alteracao   DATETIME,
    midia_file_ID     INTEGER NOT NULL,
    id_usuario INTEGER NOT NULL,
    FOREIGN KEY (midia_file_ID) REFERENCES midia(id),
    FOREIGN KEY (id_usuario)    REFERENCES usuario(id)
);