

create table notificacao(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    texto_notificacao TEXT,
    confimada INTEGER,
    tipo_notificacao ENUM('MENSAGEM','CONVITE','INFO'),
    id_origem INTEGER,
    id_usuario_para INTEGER,
    id_usuario_de INTEGER,
    FOREIGN KEY (id_usuario_de) REFERENCES usuario(id),
    FOREIGN KEY (id_usuario_para) REFERENCES usuario(id)
);
