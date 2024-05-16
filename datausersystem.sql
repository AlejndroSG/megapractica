drop table usuarioR;
drop table recipe;
drop table tags;
drop table uniones;

create table usuarioR
(
    nombre      varchar2(30),
    apellidos   varchar2(70),
    email       varchar2(50),
    nameusu     varchar2(100) primary key,
    rol         varchar2(10) not null check (rol = 'usuarioR' or rol = 'admin'),
    password    varchar2(20) not null check (length(password) >= 8)
);

insert into usuarioR values ('Juan', 'P�rez', 'juanperez@gmail.com', 'juanperez', 'admin', '12345678');
insert into usuarioR values ('Mar�a', 'L�pez', 'marialopez@yahoo.com', 'marialopez', 'usuarioR', 'password123');
insert into usuarioR values ('Carlos', 'Mart�nez', 'carlosmartinez@yahoo.com', 'carlosmartinez', 'usuarioR', 'secure1234');
insert into usuarioR values ('Ana', 'L�pez', 'anita.lopez@hotmail.com', 'anita_lopez', 'usuarioR', 'claveSegura');
insert into usuarioR values ('Pedro', 'S�nchez', 'psanchez@gmail.com', 'pedrosanchez', 'usuarioR', 'p455w0rd');
insert into usuarioR values ('Luisa', 'Ram�rez', 'luisaramirez@gmail.com', 'luisaramirez', 'admin', 'mysecurepwd');
insert into usuarioR values ('Alejandro', 'G�mez', 'alegomez@yahoo.com', 'alegomez', 'usuarioR', 'contrase�a123');
insert into usuarioR values ('Sof�a', 'Hern�ndez', 'sofiahernandez@hotmail.com', 'sofiahernandez', 'usuarioR', 'segur@123');
insert into usuarioR values ('Javier', 'D�az', 'javierdiaz@gmail.com', 'javierdiaz', 'admin', 'myPassword123');
insert into usuarioR values ('Mar�a', 'Gonz�lez', 'mariagonzalez@gmail.com', 'mariagonzalez', 'usuarioR', 'mypass123');

create table recipe
(
    cod             integer GENERATED ALWAYS AS IDENTITY (Start with 1 increment by 1) primary key,
    nombreR         varchar2(50) unique not null,
    nameusu         varchar2(100) not null,
    descripcion     varchar2(100) not null,
    ingredientes    varchar2(500) not null, 
    instruccionesR  varchar2(1000) not null,
    constraint ce_rec_usu foreign key(nameusu)
        references usuarioR(nameusu)
);

insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Pollo al horno', 'mariagonzalez', 'Deliciosa receta de pollo asado al horno', '1 pollo entero, 2 limones, 4 dientes de ajo, sal, pimienta', '1. Precalienta el horno a 180�C. 2. Lava y seca bien el pollo. 3. Exprime los limones y mezcla el jugo con los dientes de ajo picados, la sal y la pimienta. 4. Unta el pollo con esta mezcla. 5. Hornea durante 1 hora o hasta que est� dorado y cocido por dentro.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Espaguetis a la carbonara', 'luisaramirez', 'Receta tradicional italiana de espaguetis con una salsa cremosa de huevo y panceta', '250g de espaguetis, 150g de panceta, 2 huevos, 50g de queso parmesano rallado, sal, pimienta', '1. Cocina los espaguetis en agua con sal hasta que est�n al dente. 2. En una sart�n grande, saltea la panceta hasta que est� dorada. 3. En un bol, bate los huevos con el queso parmesano, sal y pimienta. 4. Escurre los espaguetis y a��delos a la sart�n con la panceta. 5. Retira la sart�n del fuego y a�ade la mezcla de huevo y queso, revolviendo r�pidamente para que la salsa se mezcle con la pasta.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Ensalada griega', 'anita_lopez', 'Ensalada fresca y saludable inspirada en la cocina mediterr�nea', 'Lechuga, tomate, pepino, cebolla morada, aceitunas negras, queso feta, aceite de oliva, vinagre de vino tinto, sal, or�gano', '1. Lava y corta todas las verduras en trozos. 2. Coloca en un bol grande. 3. A�ade las aceitunas negras y el queso feta desmenuzado. 4. Ali�a con aceite de oliva, vinagre de vino tinto, sal y or�gano al gusto. 5. Mezcla bien y sirve.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Tacos de carne asada', 'pedrosanchez', 'Tacos mexicanos con carne asada jugosa y sabrosa', '500g de carne para asar, tortillas de ma�z, cebolla, cilantro, limones, sal, salsa picante', '1. Sazona la carne con sal y pimienta y �sala a la parrilla o en una sart�n caliente. 2. Corta la carne en tiras finas. 3. Calienta las tortillas de ma�z. 4. Rellena las tortillas con la carne asada, cebolla picada, cilantro fresco y salsa picante al gusto. 5. Exprime lim�n por encima y sirve caliente.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Risotto de champi�ones', 'luisaramirez', 'Plato italiano cremoso y reconfortante con champi�ones y arroz', '300g de arroz arborio, 200g de champi�ones, 1 cebolla, 2 dientes de ajo, caldo de verduras, vino blanco, mantequilla, queso parmesano rallado, sal, pimienta', '1. En una sart�n grande, saltea la cebolla picada y el ajo en mantequilla y aceite de oliva hasta que est�n caramelizadas. 2. Agrega los champi�ones cortados en l�minas y cocina hasta que est�n dorados. 3. A�ade el arroz y cocina durante 1 minuto. 4. Agrega el vino blanco y deja que se evapore. 5. A�ade caldo de verduras caliente poco a poco, removiendo constantemente, hasta que el arroz est� cremoso y cocido. 6. Retira del fuego y a�ade queso parmesano rallado. 7. Sirve caliente.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Sushi variado', 'alegomez', 'Exquisitas variedades de sushi japon�s', 'Arroz para sushi, alga nori, salm�n fresco, at�n fresco, aguacate, pepino, zanahoria, vinagre de arroz, az�car, salsa de soja, wasabi, jengibre encurtido', '1. Cocina el arroz para sushi seg�n las instrucciones del paquete y adereza con una mezcla de vinagre de arroz, az�car y sal. 2. Corta el pescado en tiras finas. 3. Extiende una capa delgada de arroz sobre el alga nori. 4. Coloca los ingredientes sobre el arroz. 5. Enrolla el sushi firmemente con la ayuda de una esterilla. 6. Corta en rodajas y sirve con salsa de soja, wasabi y jengibre encurtido.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Sopa de cebolla francesa', 'sofiahernandez', 'Cl�sica sopa francesa gratinada con queso', 'Cebollas, caldo de carne, vino blanco, pan baguette, queso gruyere, mantequilla, aceite de oliva, sal, pimienta, tomillo', '1. Corta las cebollas en rodajas finas y sofr�elas en mantequilla y aceite de oliva hasta que est�n caramelizadas. 2. Agrega el vino blanco y cocina hasta que se evapore. 3. Vierte el caldo de carne y deja cocinar a fuego lento durante 30 minutos. 4. Corta el pan baguette en rebanadas y tu�stalo. 5. Sirve la sopa caliente en tazones aptos para horno. 6. Coloca una rebanada de pan encima de cada taz�n y cubre con queso gruyere rallado.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Ensalada C�sar', 'javierdiaz', 'Ensalada cl�sica con pollo, lechuga romana y aderezo cremoso', 'Lechuga romana, pechuga de pollo, pan tostado, queso parmesano, salsa Worcestershire, ajo, mostaza Dijon, anchoas, lim�n, aceite de oliva, sal, pimienta', '1. Lava y corta la lechuga en trozos grandes. 2. Corta el pollo en tiras y cocina a la parrilla o en una sart�n hasta que est� cocido. 3. Prepara la salsa mezclando el ajo picado, la mostaza Dijon, las anchoas picadas, el jugo de lim�n, la salsa Worcestershire, sal y pimienta. 4. Mezcla la lechuga con la salsa y agrega el pollo cortado en tiras. 5. Espolvorea con queso parmesano rallado y crutones de pan tostado. 6. Sirve y disfruta.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Tarta de manzana', 'mariagonzalez', 'Postre cl�sico con una mezcla perfecta de manzanas dulces y masa crujiente', 'Masa para tarta, manzanas, az�car, canela, lim�n, mantequilla', '1. Precalienta el horno a 180�C. 2. Pela y corta las manzanas en rodajas finas. 3. Mezcla las rodajas de manzana con az�car, canela y jugo de lim�n. 4. Extiende la masa para tarta en un molde y rellena con las manzanas. 5. Cubre con peque�os trozos de mantequilla. 6. Hornea durante 45 minutos o hasta que la masa est� dorada y las manzanas est�n tiernas. 7. Sirve caliente con helado de vainilla.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Hummus de garbanzos', 'carlosmartinez', 'Deliciosa crema de garbanzos con sabor a lim�n y ajo', 'Garbanzos cocidos, tahini, ajo, jugo de lim�n, comino, aceite de oliva, sal, pimienta, piment�n dulce', '1. Coloca los garbanzos, el tahini, el ajo picado, el jugo de lim�n, el comino, la sal y la pimienta en un procesador de alimentos. 2. Tritura hasta obtener una mezcla suave y cremosa. 3. A�ade un poco de agua si es necesario para obtener la consistencia deseada. 4. Prueba y ajusta el condimento si es necesario. 5. Sirve espolvoreado con piment�n dulce y un chorrito de aceite de oliva.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Lasa�a de verduras', 'anita_lopez', 'Sabrosa lasa�a vegetariana con capas de verduras y salsa de tomate', 'Pasta para lasa�a, calabac�n, berenjena, pimientos, champi�ones, cebolla, ajo, salsa de tomate, queso mozzarella, queso parmesano, aceite de oliva, sal, pimienta, or�gano', '1. Precalienta el horno a 180�C. 2. Corta todas las verduras en rodajas finas. 3. Saltea las verduras en una sart�n con aceite de oliva y ajo picado. 4. En un molde para hornear, coloca una capa de salsa de tomate, luego una capa de l�minas de lasa�a y una capa de verduras salteadas. 5. Repite las capas hasta terminar con una capa de salsa de tomate y queso rallado. 6. Hornea durante 30-40 minutos o hasta que est� dorado y burbujeante. 7. Deja reposar antes de servir.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Salmorejo', 'pedrosanchez', 'Sopa reconfortante de tomates maduros y hierbas arom�ticas', 'Tomates maduros, cebolla, zanahoria, caldo de verduras, tomate concentrado, ajo, albahaca fresca, aceite de oliva, sal, pimienta', '1. En una olla grande, calienta aceite de oliva y sofr�e la cebolla y la zanahoria picadas hasta que est�n tiernas. 2. A�ade el ajo picado y cocina un minuto m�s. 3. Agrega los tomates picados, el tomate concentrado y el caldo de verduras. 4. Deja cocinar a fuego lento durante 20-25 minutos. 5. Tritura la sopa con una licuadora de mano o en un procesador de alimentos hasta que est� suave. 6. Condimenta con sal, pimienta y albahaca fresca picada. 7. Sirve caliente.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Tacos de pescado', 'luisaramirez', 'Tacos mexicanos con pescado marinado y salsa fresca', 'Filetes de pescado blanco, tortillas de ma�z, repollo morado, tomate, cilantro, cebolla morada, lim�n, crema agria, salsa picante, aceite de oliva, sal, pimienta, comino, paprika', '1. Corta el pescado en tiras y marina con aceite de oliva, comino, paprika, sal y pimienta. 2. En una sart�n caliente con aceite, cocina el pescado hasta que est� dorado y cocido. 3. Calienta las tortillas de ma�z. 4. Rellena las tortillas con el pescado cocido, repollo morado rallado, tomate en cubitos, cebolla morada en rodajas, cilantro picado y salsa. 5. Exprime lim�n por encima y sirve caliente.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Pizza casera', 'alegomez', 'Pizza de masa crujiente con tus ingredientes favoritos', 'Masa para pizza, salsa de tomate, mozzarella, champi�ones, jam�n, aceitunas, or�gano, aceite de oliva', '1. Precalienta el horno a 220�C. 2. Estira la masa para pizza en una bandeja para hornear. 3. Extiende la salsa de tomate sobre la masa. 4. Cubre con mozzarella rallada y tus ingredientes favoritos. 5. Espolvorea con or�gano y un chorrito de aceite de oliva. 6. Hornea durante 15-20 minutos o hasta que la masa est� dorada y crujiente. 7. Sirve caliente y disfruta de tu pizza casera.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Arroz con pollo', 'sofiahernandez', 'Plato tradicional latinoamericano con arroz, pollo y verduras', 'Pechugas de pollo, arroz, cebolla, pimiento, ajo, tomate, zanahoria, guisantes, caldo de pollo, cilantro, aceite de oliva, sal, pimienta, comino', '1. En una olla grande, calienta aceite de oliva y sofr�e la cebolla, el ajo y el pimiento picados hasta que est�n tiernos. 2. Agrega el pollo cortado en trozos y cocina hasta que est� dorado por todos lados. 3. A�ade el arroz y cocina un par de minutos. 4. Agrega el tomate picado, las zanahorias en rodajas, los guisantes y el caldo de pollo. 5. Condimenta con sal, pimienta y comino. 6. Cocina a fuego lento hasta que el arroz est� cocido y el l�quido se haya absorbido. 7. Sirve caliente espolvoreado con cilantro picado.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Tarta de chocolate', 'javierdiaz', 'Tarta de chocolate decadente con una base de galleta y crema batida', 'Galletas, mantequilla, chocolate negro, nata para montar, az�car, esencia de vainilla, gelatina', '1. Tritura las galletas hasta obtener migas finas. 2. Mezcla las migas de galleta con mantequilla derretida y presiona en el fondo de un molde para tarta. 3. En un cazo, calienta la nata hasta que est� casi hirviendo. 4. Retira del fuego y agrega el chocolate troceado. Deja reposar unos minutos y luego mezcla hasta obtener una crema suave. 5. Vierte la crema de chocolate sobre la base de galleta y refrigera durante al menos 4 horas o hasta que est� firme. 6. Decora con crema batida y ralladuras de chocolate antes de servir.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Gazpacho andaluz', 'mariagonzalez', 'Sopa fr�a de tomate, pimiento, pepino y ajo', 'Tomates maduros, pimiento verde, pepino, cebolla, ajo, pan duro, vinagre de vino blanco, aceite de oliva, sal, pimienta', '1. Trocea los tomates, el pimiento, el pepino, la cebolla y el ajo. 2. Remoja el pan duro en agua. 3. Coloca todos los ingredientes en una licuadora y tritura hasta obtener una mezcla suave. 4. A�ade vinagre de vino blanco, aceite de oliva, sal y pimienta al gusto y mezcla bien. 5. Refrigera durante al menos 2 horas antes de servir. 6. Sirve fr�o con trozos de verduras picadas como guarnici�n.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Pasta carbonara', 'carlosmartinez', 'Pasta italiana con una salsa cremosa de huevo, panceta y queso parmesano', 'Espaguetis, panceta, huevos, queso parmesano rallado, ajo, pimienta negra, aceite de oliva', '1. Cocina los espaguetis en agua con sal hasta que est�n al dente. 2. En una sart�n grande, saltea la panceta hasta que est� dorada y crujiente. 3. En un bol, bate los huevos con el queso parmesano rallado y pimienta negra al gusto. 4. Escurre los espaguetis y a��delos a la sart�n con la panceta. 5. Retira la sart�n del fuego y a�ade la mezcla de huevo y queso, revolviendo r�pidamente para que la salsa se mezcle con la pasta. 6. Sirve caliente con m�s queso parmesano rallado por encima.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Ensalada de quinoa', 'anita_lopez', 'Ensalada saludable y nutritiva con quinoa, verduras y aderezo de lim�n', 'Quinoa, tomate, pepino, pimiento rojo, aguacate, cebolla roja, cilantro, lim�n, aceite de oliva, sal, pimienta', '1. Cocina la quinoa seg�n las instrucciones del paquete y deja enfriar. 2. Corta los tomates, el pepino, el pimiento, el aguacate y la cebolla en trozos. 3. Mezcla la quinoa cocida con las verduras cortadas y el cilantro picado. 4. Ali�a con jugo de lim�n, aceite de oliva, sal y pimienta al gusto. 5. Mezcla bien y sirve fr�o.');
insert into recipe (nombreR, nameusu, descripcion, ingredientes, instruccionesR) values ('Tarta de lim�n', 'pedrosanchez', 'Tarta refrescante con una cremosa y �cida mezcla de lim�n', 'Galletas, mantequilla, leche condensada, huevos, jugo de lim�n, ralladura de lim�n', '1. Tritura las galletas hasta obtener migas finas. 2. Mezcla las migas de galleta con mantequilla derretida y presiona en el fondo de un molde para tarta. 3. En un bol, bate la leche condensada con los huevos, el jugo y la ralladura de lim�n. 4. Vierte la mezcla sobre la base de galleta. 5. Hornea durante 20-25 minutos a 180�C o hasta que est� firme. 6. Deja enfriar antes de refrigerar durante al menos 2 horas. 7. Sirve fr�o, decorado con rodajas de lim�n y crema batida si lo deseas.');

create table tags
(
    id              integer GENERATED ALWAYS AS IDENTITY (Start with 1 increment by 1) primary key,
    nom             varchar2(50) not null
);

insert into tags (nom) values ('Carne');
insert into tags (nom) values ('Pasta');
insert into tags (nom) values ('Dulces');
insert into tags (nom) values ('Ensaladas');
insert into tags (nom) values ('Postres');
insert into tags (nom) values ('Pescados');
insert into tags (nom) values ('Bebidas');
insert into tags (nom) values ('Espa�ol');
insert into tags (nom) values ('Asi�tico');
insert into tags (nom) values ('Arroz');

create table uniones
(
    id              number(3,0),
    cod             number(3,0),
    constraint cp_uni primary key(id, cod),
    constraint ce_uni_tag foreign key(id)
        references tags(id),
    constraint ce_uni_rec foreign key(cod)
        references recipe(cod)
);

insert into uniones values (1, 1);
insert into uniones values (1, 4);
insert into uniones values (1, 15);
insert into uniones values (2, 2);
insert into uniones values (2, 18);
insert into uniones values (3, 9);
insert into uniones values (3, 16);
insert into uniones values (3, 20);
insert into uniones values (4, 3);
insert into uniones values (4, 8);
insert into uniones values (4, 19);
insert into uniones values (5, 9);
insert into uniones values (5, 16);
insert into uniones values (5, 20);
insert into uniones values (6, 6);
insert into uniones values (6, 13);
insert into uniones values (7, 17);
insert into uniones values (8, 12);
insert into uniones values (8, 17);
insert into uniones values (9, 6);
insert into uniones values (10, 5);
insert into uniones values (10, 6);
insert into uniones values (10, 15);

commit;
