drop table usuarioR;
drop table receta;

create table usuarioR
(
    nombre      varchar2(30),
    apellidos   varchar2(70),
    email       varchar2(50),
    nameusu     varchar2(100) primary key,
    rol         varchar2(10) not null check (rol = 'usuarioR' or rol = 'admin'),
    password    varchar2(20) not null check (length(password) >= 8)
);

insert into usuarioR values ('Juan', 'Pérez', 'juanperez@gmail.com', 'juanperez', 'admin', '12345678');
insert into usuarioR values ('María', 'López', 'marialopez@yahoo.com', 'marialopez', 'usuarioR', 'password123');

create table receta
(
    cod         number(3,0) primary key,
    nombreR     varchar2(50) unique not null,
    duracion    number(3,0) not null check (duracion > 0),
    dificultad  number(2,0) not null check (dificultad > 0 and dificultad <= 10),
    fecha       date not null,
    nameusu     varchar2(100) not null,
    constraint ce_rec_usu foreign key(nameusu)
        references usuarioR(nameusu)
);

insert into receta values (001, 'Pastel de chocolate', 60, 8, '10/05/2024', 'marialopez');
insert into receta values (002, 'Ensalada César', 20, 4, '10/05/2024', 'juanperez');

commit;

