-- Tabela funcionario
CREATE TABLE funcionario (
    rowid bigint primary key auto_increment, 
    nm_funcionario VARCHAR(255) NOT NULL
);

INSERT INTO funcionario (nm_funcionario) VALUES ('João'), ('Maria'), ('José'), ('Joana');

-- Tabela agenda
CREATE TABLE agenda (
    id_agenda bigint primary key auto_increment, 
    nm_agenda VARCHAR(255) NOT NULL, 
    pd_disponivel VARCHAR(10) NOT NULL
);

INSERT INTO agenda(nm_agenda, pd_disponivel) VALUES ('Pediatria', 'Manhã');
INSERT INTO agenda(nm_agenda, pd_disponivel) VALUES ('Oftalmo', 'Tarde');

-- Tabela compromisso
CREATE TABLE compromisso (
    id_compromisso bigint primary key auto_increment, 
    rowid bigint NOT NULL, 
    id_agenda bigint NOT NULL, 
    data date NOT NULL, 
    horario time NOT NULL, 
    foreign key(rowid) references funcionario(rowid) ON DELETE CASCADE,
    foreign key(id_agenda) references agenda(id_agenda) ON DELETE RESTRICT
);

INSERT INTO compromisso (rowid, id_agenda, data, horario) VALUES(1, 1, '2024-01-15', '09:00:00');
INSERT INTO compromisso (rowid, id_agenda, data, horario) VALUES(2, 2, '2024-01-16', '15:00:00');