CREATE TABLE funcionario (rowid bigint primary key auto_increment, nm_funcionario VARCHAR(255));
INSERT INTO funcionario (nm_funcionario) VALUES ('João'), ('Maria'), ('José'), ('Joana');

CREATE TABLE agenda (id_agenda bigint primary key auto_increment, nm_agenda VARCHAR(255), pd_disponivel VARCHAR(10));
INSERT INTO agenda(nm_agenda, pd_disponivel) VALUES ('Pediatria', 'Manhã');
INSERT INTO agenda(nm_agenda, pd_disponivel) VALUES ('Oftmalmo', 'Tarde');

CREATE TABLE compromisso (id_compromisso bigint primary key auto_increment, rowid bigint, id_agenda bigint, data date, horario time, foreign key(rowid) references funcionario(rowid),
foreign key(id_agenda) references agenda(id_agenda));
INSERT INTO compromisso (rowid, id_agenda, data, horario) VALUES(1, 1, '2024-01-15', '09:00:00');
INSERT INTO compromisso (rowid, id_agenda, data, horario) VALUES(2, 2, '2024-01-16', '15:00:00');