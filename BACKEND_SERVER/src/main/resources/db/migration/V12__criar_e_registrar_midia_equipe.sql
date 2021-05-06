create table `midia_equipe`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    data_alteracao     DATETIME NOT NULL,
    midia_file_ID      INTEGER NOT NULL,
    id_equipe INTEGER NOT NULL,
    FOREIGN KEY (midia_file_ID) REFERENCES midia(id),
    FOREIGN KEY (id_equipe) REFERENCES equipe(id)
);