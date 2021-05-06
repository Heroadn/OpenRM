create table `membro`(
   `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
   `id_equipe`  INTEGER NOT NULL,
   `id_usuario` INTEGER NOT NULL,
   FOREIGN KEY (`id_equipe`)  REFERENCES equipe(`id`),
   FOREIGN KEY (`id_usuario`) REFERENCES usuario(`id`)
);
