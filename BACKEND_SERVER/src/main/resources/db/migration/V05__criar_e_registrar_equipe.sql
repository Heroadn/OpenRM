create table `equipe`(
   `id`         INTEGER PRIMARY KEY AUTO_INCREMENT,
   `nome`       VARCHAR(50)  NOT NULL,
   `id_usuario` INTEGER,
   `id_projeto` INTEGER,
   FOREIGN KEY (`id_usuario`) REFERENCES usuario(`id`),
   FOREIGN KEY (`id_projeto`) REFERENCES projeto(`id`)
);
