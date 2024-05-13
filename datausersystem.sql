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
insert into usuarioR values ('Carlos', 'Martínez', 'carlosmartinez@yahoo.com', 'carlosmartinez', 'usuarioR', 'secure1234');
insert into usuarioR values ('Ana', 'López', 'anita.lopez@hotmail.com', 'anita_lopez', 'usuarioR', 'claveSegura');
insert into usuarioR values ('Pedro', 'Sánchez', 'psanchez@gmail.com', 'pedrosanchez', 'admin', 'p455w0rd');
insert into usuarioR values ('Luisa', 'Ramírez', 'luisaramirez@gmail.com', 'luisaramirez', 'admin', 'mysecurepwd');
insert into usuarioR values ('Alejandro', 'Gómez', 'alegomez@yahoo.com', 'alegomez', 'usuarioR', 'contraseña123');
insert into usuarioR values ('Sofía', 'Hernández', 'sofiahernandez@hotmail.com', 'sofiahernandez', 'usuarioR', 'segur@123');
insert into usuarioR values ('Javier', 'Díaz', 'javierdiaz@gmail.com', 'javierdiaz', 'admin', 'myPassword123');
insert into usuarioR values ('María', 'González', 'mariagonzalez@gmail.com', 'mariagonzalez', 'usuarioR', 'mypass123');


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
insert into receta values (003, 'Lasaña de carne', 60, 7, '10/05/2024', 'anita_lopez');
insert into receta values (004, 'Sushi variado', 50, 6, '09/05/2024', 'pedrosanchez');
insert into receta values (005, 'Pollo al curry', 40, 6, '08/05/2024', 'luisaramirez');
insert into receta values (006, 'Pasta carbonara', 25, 4, '07/05/2024', 'alegomez');
insert into receta values (007, 'Ceviche peruano', 35, 7, '06/05/2024', 'sofiahernandez');
insert into receta values (008, 'Gazpacho andaluz', 15, 3, '05/05/2024', 'javierdiaz');
insert into receta values (009, 'Filete de salmón', 20, 5, '04/05/2024', 'mariagonzalez');
insert into receta values (010, 'Hamburguesa gourmet', 35, 6, '03/05/2024', 'carlosmartinez');
insert into receta values (011, 'Tiramisú italiano', 40, 4, '02/05/2024', 'anita_lopez');
insert into receta values (012, 'Risotto de champiñones', 30, 6, '01/05/2024', 'pedrosanchez');
insert into receta values (013, 'Empanadas argentinas', 45, 7, '30/04/2024', 'luisaramirez');
insert into receta values (014, 'Pad thai tailandés', 50, 8, '29/04/2024', 'alegomez');
insert into receta values (015, 'Sopa de cebolla francesa', 35, 5, '28/04/2024', 'sofiahernandez');
insert into receta values (016, 'Enchiladas mexicanas', 40, 6, '27/04/2024', 'javierdiaz');
insert into receta values (017, 'Canelones de espinacas', 30, 5, '26/04/2024', 'mariagonzalez');
insert into receta values (018, 'Parrillada argentina', 60, 8, '25/04/2024', 'carlosmartinez');
insert into receta values (019, 'Tortilla de patatas', 30, 5, '12/05/2024', 'marialopez');
insert into receta values (020, 'Paella Valenciana', 45, 8, '11/05/2024', 'juanperez');


commit;

