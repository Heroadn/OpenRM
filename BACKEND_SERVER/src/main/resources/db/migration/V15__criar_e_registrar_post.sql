create table post(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `titulo` VARCHAR(80),
    `texto` TEXT,
    `data_post` DATETIME NOT NULL,
    `id_usuario` INTEGER,
    `id_projeto` INTEGER,
    FOREIGN KEY (`id_projeto`) references `projeto`(`id`),
    FOREIGN KEY (`id_usuario`) references `usuario`(`id`)
);

