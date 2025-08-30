CREATE TABLE funcionario (rowid bigint primary key auto_increment, nm_funcionario VARCHAR(255));
INSERT INTO funcionario (nm_funcionario) VALUES ('João'), ('Maria'), ('José'), ('Joana');

CREATE TABLE agenda (id_agenda bigint primary key auto_increment, nm_agenda VARCHAR(255), pd_disponivel VARCHAR(10));

CREATE TABLE compromisso (rowid bigint, id_agenda bigint, data date, horario time, foreign key(rowid) references funcionario(rowid),
foreign key(id_agenda) references agenda(id_agenda));