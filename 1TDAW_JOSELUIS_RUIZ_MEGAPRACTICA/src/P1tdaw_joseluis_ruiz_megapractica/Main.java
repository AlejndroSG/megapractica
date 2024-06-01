package P1tdaw_joseluis_ruiz_megapractica;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Scanner sc = new Scanner (System.in);
        Conexion c = new Conexion();
        String consulta1 = "";
        System.out.println("Bienvenido a recetasPANAL");
        //Empezamos pidiendo si esta registrado o no, esto se repite hasta que ponga S o N
        System.out.println("¿Estás registrado?(S/N)");
        Usuarios usu = new Usuarios();
        char opc;
        do{
            opc = sc.next().toUpperCase().charAt(0);
        }while(opc != 'S' && opc != 'N');
        //Cuando la opcion sea S empieza el menu de inicio de sesion
        if(opc == 'S'){
            //Empezamos con las tipicas preguntas de inicio de sesion
            sc.nextLine();
            System.out.println("Introduce nombre de usuario");
            String nom_usu = sc.nextLine().toLowerCase();
            System.out.println("Introduce la contraseña");
            String contr = sc.nextLine();
            Statement st = c.getCon().createStatement();
            //Una vez escrito tus datos se abre la consulta que comprueba si ese usuario existe
            consulta1 = "select count(*) from usuarioR where password = '" + contr + "' and nameusu = '" + nom_usu + "'";
            ResultSet rs = st.executeQuery(consulta1);
            rs.next();
            int num = rs.getInt(1);
            //Si coincide el nombre y consulta entonces pasamos a lo siguiente
            if(num == 1){
                System.out.println("Se ha iniciado sesión con éxito");
                String consulta = "select rol from usuarioR where password = '" + contr + "' and nameusu = '" + nom_usu + "'";
                rs = st.executeQuery(consulta);
                rs.next();
                String rol = rs.getString(1);
                usu = new Usuarios(nom_usu, contr, rol);
                System.out.println("Usuario creado exitosamente en Java");
            }else{
                //En el caso que no exista ese usuario, pregunta si necesita uno nuevo
                System.out.println("No existe el usuario, ¿quieres crear uno nuevo?(S/N)");
                char opcion = sc.next().toUpperCase().charAt(0);
                //Si dice que si pasamos a las tipicas preguntas de crear usuarios
                if(opcion == 'S'){
                    sc.nextLine();
                    System.out.println("Introduce el nombre");
                    String nom = sc.nextLine();
                    System.out.println("Introduce el apellido");
                    String apell = sc.nextLine();
                    System.out.println("Introduce el correo electrónico");
                    String corr = sc.nextLine();
                    //Insertamos los datos dentro de la tabla
                    String consulta = "insert into usuarioR values ('"+ nom + "', '"+ apell + "', '"+ corr +"', '"+ nom_usu +"', 'usuarioR', '"+ contr +"')";
                    c.insert(consulta);
                    usu = new Usuarios(nom_usu, contr, "usuarioR");
                }else{
                    //Si aun asi dice que no se quiere registrar, pierde privilegios y acceso a ciertas partes del programa
                    System.out.println("No has iniciado sesión, por lo que no tendrás acceso total al programa");
                }
            }
        //Si elige N desde el principio, pasamos a registro
        }else if(opc == 'N'){
            System.out.println("¿Quiéres registrarte?(S/N)");
            do{
                opc = sc.next().toUpperCase().charAt(0);
            }while(opc != 'S' && opc != 'N');
            //Si elige S pregunta por nombre de usuario y contraseña
            if(opc == 'S'){
                sc.nextLine();
                System.out.println("Introduce nombre de usuario");
                String nom_usu = sc.nextLine().toLowerCase();
                System.out.println("Introduce la contraseña");
                String contr = sc.nextLine();
                //Comprobamos que esos datos no existan, si no existen entonces podemos crear la cuenta
                consulta1 = "select count(*) from usuarioR where password = '" + contr + "' and nameusu = '" + nom_usu + "'";
                Statement st = c.getCon().createStatement();
                ResultSet rs = st.executeQuery(consulta1);
                rs.next();
                int count = rs.getInt(1);
                System.out.println(count);
                
                if(count == 0){
                    System.out.println("Tu cuenta no existe , vamos a crear una!!");
                    System.out.println("Introduce el nombre");
                    String nom = sc.nextLine();
                    System.out.println("Introduce el apellido");
                    String apell = sc.nextLine();
                    System.out.println("Introduce el correo electrónico");
                    String corr = sc.nextLine();
                    String consulta = "insert into usuarioR values ('"+ nom + "', '"+ apell + "', '"+ corr +"', '"+ nom_usu +"', 'usuarioR', '"+ contr +"')";
                    c.insert(consulta);
                    usu = new Usuarios(nom_usu, contr, "usuarioR");
                }            
            //Si elige N no recibe completamente los beneficios del sistema
            }else{
                System.out.println("No has iniciado sesión, por lo que no tendrás acceso total al programa");
            }
        }
        String consulta;
        int cont;
        Recetas r = new Recetas();
        int selecCod;
        int num;
        String opcion;
        System.out.println(" ");
        //Un do while que engloba a todo lo referente al menu, que mientras no elija el numero 2, no se cierra el programa
        do{
            do{
                System.out.println("Entrar a la aplicación (1)");
                System.out.println("Salir de la aplicación (2)");
                num = sc.nextInt();
            }while(num != 1 && num != 2);
            sc.nextLine();
            //Si elige 1, empieza a aparecer el siguiente menu que depende del tipo de usuario que seas
            switch(num){
                case 1:
                    do{
                    //Esta parte del menu es comun a todos los usuarios, siempre se muestra
                    System.out.println("\nMenú de recetasPANAL");
                    System.out.println("-----------------------------------------------");
                    System.out.println("Buscar recetas (BR)");
                    System.out.println("Ver todas las recetas (VT)");
                    if(usu.getRol() != null){
                        //En el caso que el rol no sea null, entonces se muestra otra parte del menu 
                        System.out.println("Ver Perfil (VP)");
                        System.out.println("Modificar Perfil (MP)");
                        System.out.println("Ver mis recetas (VR)");
                        System.out.println("Añadir recetas (AR)");
                        if(usu.getRol().equals("usuarioR")){
                            //Esto es cuando el usuario ya estaba precreado por lo que puedes borrar tus recetas
                            System.out.println("Borrar mis recetas (BM)");
                        }
                        if(usu.getRol().equals("admin")){
                            //Esta parte solo se muestra si el rol del usuario es de "admin"
                            System.out.println("Modificar recetas (MR)");
                            System.out.println("Ver todos los usuarios registrados (USUR)");
                            System.out.println("Borrar alguna recetas (BAR)");
                        }
                    }
                    System.out.println("Cerrar (C)");
                    System.out.println("Elige la opcion correcta");
                    opcion = sc.nextLine().toUpperCase();
                    String opci;
                    int opcn;
                    int[] codE = new int[1000];
                    //Dentro del switch de entrar a la app hay un segundo switch con mas opciones
                    switch(opcion){
                        case "BR": //Esto se hace en la 3ª parte
                            do{
                                //En buscar receta se elige uno de los 3 metodos que hay
                                System.out.println("¿Mediante que método quieres buscar, Nombre de la receta(NR), Etiqueta(E) o Nombre de Usuario(NU)?");
                                opci = sc.nextLine().toUpperCase();
                                System.out.println("");
                            }while(!opci.equals("NR") && !opci.equals("E") && !opci.equals("NU"));
                            
                                switch(opci){
                                    case "NR":
                                        //Al elegir por nombre de receta se hace un nuevo switch pidiendo el nombre o palabra clave
                                        System.out.println("Introduce el nombre o una palabra clave del nombre de la receta.");
                                        String palabC = sc.nextLine().toLowerCase();
                                        System.out.println("");
                                        //Buscamos cuantas recetas existen con ese nombre o con esa palabra
                                        Statement st20 = c.getCon().createStatement();
                                        String consulta10 = "select count(*) from recipe where lower(nombreR) like '%" + palabC + "%'";
                                        ResultSet rs20 = st20.executeQuery(consulta10);
                                        rs20.next();
                                        //Sacamos el codigo y el nombre donde tenga ese nombre o palabra clave
                                        String consulta20 = "select cod, nombreR from recipe where lower(nombreR) like '%" + palabC + "%'";
                                        ResultSet rs21 = st20.executeQuery(consulta20);
                                        
                                        while(rs21.next()){
                                            System.out.println(rs21.getInt(1) + " - " + rs21.getString(2));                                
                                        }
                                        //Una vez sabemos las recetas pedimos que elija de cual quiere saber
                                        System.out.println("");
                                        System.out.println("Selecciona el código de la receta que quieres seleccionar.");
                                        selecCod = sc.nextInt();
                                        //Sacamos todo de la receta que se elige
                                        String consulta22 = "select * from recipe where cod = '" + selecCod + "'";
                                        ResultSet rs22 = st20.executeQuery(consulta22);
                                        rs22.next();
                                        //Aqui lo mostramos ordenado
                                        System.out.println("");
                                        System.out.println("Código: "+rs22.getInt(1));
                                        System.out.println("Nombre de la receta: "+rs22.getString(2));
                                        System.out.println("Nombre del usuario: "+rs22.getString(3));
                                        System.out.println("Puntuación de la receta: "+rs22.getInt(4));
                                        System.out.println("Descripción de la receta: "+rs22.getString(5));
                                        System.out.println("Ingredientes: "+rs22.getString(6));
                                        System.out.println("Instrucciones: "+rs22.getString(7));
                                        
                                        sc.nextLine();
                                    break;
                                    case "E":
                                        //Si elegimos por etiquetas ponemos que se pueda poner 1 o mas
                                        Statement st21 = c.getCon().createStatement();
                                        r.imprimeTags();
                                        
                                        System.out.println("");
                                        int contt = 0;
                                        do{
                                            System.out.println("Introduce el código de la etiqueta que quieres buscar, si no quieres buscar ninguna más introduce (-1)");
                                            opcn = sc.nextInt();
                                            
                                            if(opc != -1){
                                                //En el momento que ponta -1 para de coger etiquetas, contamos cuantas recetas hay con esa etiqueta/etiquetas
                                                String consulta23 = "select count(*) from recipe, tags, uniones where recipe.cod = uniones.cod and tags.id = uniones.id and tags.id = '" + opcn + "'";
                                                ResultSet rs23 = st21.executeQuery(consulta23);
                                                rs23.next();

                                                if(rs23.getInt(1) != 0){
                                                    codE[contt] = opcn;
                                                    contt++;
                                                }
                                            }
                                        }while(opcn != -1);
                                        
                                        int[] codEAux = new int[contt];
                                        codEAux = copiarArray(codE, codEAux);
                                        //Aqui buscamos en concreto las recetas con las etiquetas introducidas con intersect para decir que sean si o si las que tengan esas
                                        String consultaaa = "select count(*) from recipe where cod in (";
                                        String subconsulta = "select distinct cod from uniones where id = ";
                                        String idU = " and id = ";
                                        String consultaa = "select recipe.cod, nombreR from recipe, uniones where recipe.cod = uniones.cod and uniones.id = ";
                                        String inter = " intersect ";
                                        String consulta24 = consultaaa;
                                        String consulta25 = "";
                                        
                                        for(int i = 0; i < codEAux.length; i++){
                                            if(i != contt-1){
                                                consulta24 += subconsulta + codEAux[i] + inter;
                                            }else{
                                                consulta24 += subconsulta + codEAux[i] + ")";
                                            }
                                        }
                                        
                                        System.out.println("");
                                        ResultSet rs24 = st21.executeQuery(consulta24);
                                        rs24.next();
                                        
                                        if(rs24.getInt(1) == 0){
                                            //Si no encuentra muestra el mensaje
                                            System.out.println("No hay ninguna receta que tenga esta etiqueta.");
                                        }else{
                                            for(int i = 0; i < codEAux.length; i++){
                                                if(i != contt-1){
                                                    consulta25 += consultaa + codEAux[i] + " " + inter + " ";
                                                }else{
                                                    consulta25 += consultaa + codEAux[i];
                                                }
                                            }
                                        }

                                        if(rs24.getInt(1) != 0){
                                            //Si encuentra entonces entra aqui para continuar
                                            ResultSet rs25 = st21.executeQuery(consulta25);
                                            while(rs25.next()){
                                                System.out.println(rs25.getInt(1) + " - " + rs25.getString(2));
                                            }

                                            System.out.println("");
                                            System.out.println("Selecciona el código de la receta que quieres seleccionar.");
                                            selecCod = sc.nextInt();
                                            //Buscamos y sacamos los datos de la receta que puso de codigo
                                            String consulta29 = "select * from recipe where cod = '" + selecCod + "'";
                                            ResultSet rs29 = st21.executeQuery(consulta29);
                                            rs29.next();
                                            //Sacamos todos los datos de esa receta
                                            System.out.println("");
                                            System.out.println("Código: "+rs29.getInt(1));
                                            System.out.println("Nombre de la receta: "+rs29.getString(2));
                                            System.out.println("Nombre del usuario: "+rs29.getString(3));
                                            System.out.println("Puntuación de la receta: "+rs29.getInt(4));
                                            System.out.println("Descripción de la receta: "+rs29.getString(5));
                                            System.out.println("Ingredientes: "+rs29.getString(6));
                                            System.out.println("Instrucciones: "+rs29.getString(7));
                                        }
                                        
                                        sc.nextLine();
                                    break;
                                    case "NU":
                                        Statement st22 = c.getCon().createStatement();
                                        //Pedimos que introduzca el nombre de usuario que quiere buscar recetas
                                        System.out.println("Introduce el usuario del que quieres buscar sus recetas.");
                                        String usuR = sc.nextLine();
                                        System.out.println(""); 
                                        //Contamos las recetas que tiene ese usuario
                                        String consulta26 = "select count(*) from recipe where nameusu = '" + usuR + "'";
                                        ResultSet rs26 = st22.executeQuery(consulta26);
                                        rs26.next();

                                        if(rs26.getInt(1) == 0){
                                            //Si sale 0 no tiene recetas
                                            System.out.println("No hay resultados disponibles.");
                                        }else{
                                            //Si sale otra cosa entonces saca el codigo y nombre de receta de ese usuario
                                            String consulta27 = "select recipe.cod, nombreR from recipe where nameusu = '" + usuR + "'";
                                            ResultSet rs27 = st22.executeQuery(consulta27);
                                            
                                            while(rs27.next()){
                                                //Mientras encuentre recetas de ese usuario las imprime
                                                System.out.println(rs27.getInt(1) + " - " + rs27.getString(2));
                                            }

                                            System.out.println("");
                                            System.out.println("Selecciona el código de la receta que quieres seleccionar.");
                                            selecCod = sc.nextInt();
                                            //Pedimos el codigo de la receta a sacar
                                            String consulta28 = "select * from recipe where cod = '" + selecCod + "'";
                                            //Obtenemos todo de esa receta
                                            ResultSet rs28 = st22.executeQuery(consulta28);
                                            rs28.next();
                                            //Y lo mostramos ordenado
                                            System.out.println("");
                                            System.out.println("Código: "+rs28.getInt(1));
                                            System.out.println("Nombre de la receta: "+rs28.getString(2));
                                            System.out.println("Nombre del usuario: "+rs28.getString(3));
                                            System.out.println("Puntuación de la receta: "+rs28.getInt(4));
                                            System.out.println("Descripción de la receta: "+rs28.getString(5));
                                            System.out.println("Ingredientes: "+rs28.getString(6));
                                            System.out.println("Instrucciones: "+rs28.getString(7));

                                            sc.nextLine();
                                        }
                                    break;
                                }
                            
                        break;
                        case "VT":
                            Statement st = c.getCon().createStatement();
                            //Sacamos cuantas recetas hay en total
                            consulta = "select count(*) from recipe";
                            ResultSet rs = st.executeQuery(consulta);
                            rs.next();
                            int n = rs.getInt(1);

                            String [] recetas = new String [n];
                            //Un array donde la longitud es la cantidad de recetas que hay
                            String consulta2;
                            consulta2 = "select * from recipe order by cod";
                            //Sacamos todo de las recetas ordenadas por su codigo
                            ResultSet rs2 = st.executeQuery(consulta2);

                            cont = 0;
                            System.out.println(" ");
                            while(rs2.next()){
                                //Mientras encuentre cosas mostramos el nombre de la receta
                                recetas[cont] = rs2.getString("nombreR"); 
                                cont++;
                            }

                            for (int i = 0; i < recetas.length; i++){
                                //Mientras sea menor que la longitud de recetas muestra
                                System.out.println(recetas[i]);
                            }
                        break;
                        case "VP":
                            if (usu.getRol() != null){
                                //Si el rol es distinto de null entra dentro de ver perfil
                                System.out.println("-------------------------------------------------------------");
                                System.out.println(" ");
                                Statement st3 = c.getCon().createStatement();
                                consulta = "";
                                //Seleccionamos todo menos la contraseña de el nombre de usuario que haya iniciado sesion
                                consulta = "select nombre, apellidos, email from usuarioR where nameusu = '"+usu.getNameusu()+"'";
                                ResultSet rs4 = st3.executeQuery(consulta);
                                rs4.next();
                                //Mostramos los datos
                                System.out.println("Datos de tu perfil");
                                System.out.println("Nombre: "+rs4.getString(1));
                                System.out.println("Apellidos: "+rs4.getString(2));
                                System.out.println("Email: "+rs4.getString(3));
                                System.out.println("Nombre de usuario: "+usu.getNameusu());
                                System.out.println("Rol en el sistema: "+usu.getRol());
                            }
                        break;
                        case "MP":
                            if(usu.getRol() != null){
                                //Si el rol es distinto de null entra
                                System.out.println("Indica qué quieres modificar de tu perfil (nombre, apellidos, email, nombre de usuario)");
                                String opcin = sc.nextLine().toLowerCase();
                                //Pedimos que quiere modificar
                                String cambio = "";
                                String columna = "";
                                Statement st12 = c.getCon().createStatement();
                                //Sacamos todo de usuarios donde el nombre de usuario sea el de inicio de sesion
                                consulta = "select * from usuarioR where nameusu = '"+usu.getNameusu()+"'";
                                ResultSet rs13 = st12.executeQuery(consulta);
                                rs13.next();


                                if(opcin.equals("nombre")){
                                    //Si elige nombre cambia su nombre
                                    System.out.println("Introduce tu nuevo nombre");
                                    cambio = sc.nextLine();
                                    columna = rs13.getMetaData().getColumnName(1).toLowerCase();
                                }
                                if(opcin.equals("apellidos")){
                                    //Si elige apellidos cambia apellidos
                                    System.out.println("Introduce tu nuevo apellido");
                                    cambio = sc.nextLine();
                                    columna = rs13.getMetaData().getColumnName(2);
                                }
                                if(opcin.equals("email")){
                                    //Si elige email cambia el email
                                    System.out.println("Introduce tu nuevo email");
                                    cambio = sc.nextLine().toLowerCase();
                                    columna = rs13.getMetaData().getColumnName(3);
                                }
                                if(opcin.equals("nombre de usuario")){
                                    //Si elige nombre de usuario cambia nombre de usuario
                                    System.out.println("Introduce tu nuevo nombre de usuario");
                                    cambio = sc.nextLine().toLowerCase();
                                    columna = rs13.getMetaData().getColumnName(4);
                                }
                                //Con esto actualizamos los datos de usuario donde la columna sea el nombre de lo pedido y su nombre de usuario sea el de inicio de sesion
                                String consulta6 = "update usuarioR set "+columna+" = '"+cambio+"' where nameusu = '"+usu.getNameusu()+"'";
                                if(st12.executeUpdate(consulta6)!=0);
                                //Si es distinto de 0 entonces la modificacion es correcta
                                    System.out.println("Modificación correcta");
                            }
                                
                        break;
                        case "VR":
                            if(usu.getRol() != null){
                                //Si el rol no es null puede entrar
                                System.out.println("Tus recetas son: ");
                                Statement st8 = c.getCon().createStatement();
                                String consulta5 = "select count(*) from recipe where nameusu = '"+usu.getNameusu()+"'";
                                //Aqui contamos el total de recetas que tiene el usuario
                                ResultSet rs10 = st8.executeQuery(consulta5);
                                rs10.next();
                                int number = rs10.getInt(1);
                                //Array con longitud del number de rs (consulta 5 es decir sacamos la cantidad de recetas que tiene)
                                String [] misrecetas = new String [number];
                                //Sacamos el codigo y nombre de receta del usuario
                                String consulta11 = "select cod, nombreR from recipe where nameusu = '"+usu.getNameusu()+"'";
                                ResultSet rs11 = st8.executeQuery(consulta11);

                                cont = 0;
                                int contador = 1;
                                while(rs11.next()){
                                    //Mientras encuentre sigue sacando
                                    misrecetas[cont] = rs11.getString(2);
                                    cont++;

                                    System.out.println(rs11.getInt(1) + ".- "+ rs11.getString(2));
                                }

                                int numm = -10;
                                do{
                                    //Pedimos de que receta quiere la informacion
                                    System.out.println("\nIntroduce de cuál quieres ver la información, o pon 0 si quieres salir del programa");
                                    do{
                                        numm = sc.nextInt();
                                    }while(numm < 0 && numm > cont);
                                    if(numm != 0){
                                        System.out.println(" ");
                                        //Al ser distinto de 0 entra y sacamos todo de recetas donde el codigo sea el numero pedido
                                        String consulta12 = "select * from recipe where cod = '"+numm+"'";
                                        ResultSet rs12 = st8.executeQuery(consulta12);
                                        rs12.next();
                                        //Sacamos los datos
                                        System.out.println("Código: "+rs12.getInt(1));
                                        System.out.println("Nombre de la receta: "+rs12.getString(2));
                                        System.out.println("Nombre del usuario: "+rs12.getString(3));
                                        System.out.println("Puntuación de la receta: "+rs12.getInt(4));
                                        System.out.println("Descripción de la receta: "+rs12.getString(5));
                                        System.out.println("Ingredientes: "+rs12.getString(6));
                                        System.out.println("Instrucciones: "+rs12.getString(7));
                                    }
                                    //Todo esto se repite mientras no ponga 0
                                }while (numm != 0);
                            }
                            sc.nextLine();
                        break;
                        case "AR":
                            if(usu.getRol() != null){
                                //Entra si el rol no es null y pedimos los datos de la receta a insertar
                                System.out.println("Dime el nombre de la receta");
                                String nameR = sc.nextLine();
                                System.out.println("Describe tu receta");
                                String descrip = sc.nextLine();
                                System.out.println("Introduce los ingredientes de tu receta");
                                String ingred = sc.nextLine();
                                System.out.println("Introduce los pasos a seguir para hacer la receta");
                                String pasos = sc.nextLine();
                                System.out.println("¿Qué puntuación le darías a tu receta? (0/5)");
                                float punt;
                                do{
                                    punt = sc.nextFloat();
                                }while(punt < 0 || punt > 5);
                                //Creamos una instancia llamando al constructor
                                Recetas rec = new Recetas(nameR, punt, descrip, ingred, pasos, usu.getNameusu(), usu.getPassword(), usu.getRol());
                                //Insertamos los datos en recetas (el id es automatico)
                                String consulta3 = "insert into recipe (nombreR, nameusu, puntuacion, descripcion, ingredientes, instruccionesR) values ('"+nameR+"' , '"+usu.getNameusu()+"' , "+punt+" , '"+descrip+"' , '"+ingred+"' , '"+pasos+"')";
                                //Llamamos al metodo de insertar
                                c.insert(consulta3);
                                System.out.println("Insertando receta...");
                                Statement st4 = c.getCon().createStatement();
                                //Seleccionamos el codigo de las recetas donde el nombre sea el introducido
                                String consulta4 = "select cod from recipe where nombreR = '"+nameR+"'";
                                ResultSet rs5 = st4.executeQuery(consulta4);
                                rs5.next();
                                int n1 = rs5.getInt(1);
                                rec.imprimeTags();

                                int opcii;
                                int[] codER = new int[100];
                                int contta = 0;
                                do{
                                    //Pedimos la posicion de etiquedas a asignar
                                    System.out.println("Introduce la posición de las etiquetas que quieres asignar a tu receta, si quieres añadir una nueva introduce (0), si quieres salir introduce (-1).");
                                    opcii = sc.nextInt();
                                    
                                    String nomT = "";
                                    if(opcii == 0){
                                        do{
                                            //Al ser 0 creamos una nueva etiqueta
                                            sc.nextLine();
                                            System.out.println("Introduce el nombre de la nueva etiqueta, si no quieres introducir otra introduce (0).");
                                            nomT = sc.nextLine();
                                            if(!nomT.equals("0")){
                                                Statement st25 = c.getCon().createStatement();
                                                //Todo esto se hace mientras no ponga 0, insertamos dentro de tags el nombre de etiqueta 
                                                String consulta25 = "insert into tags (nom) values ('" + nomT + "')";
                                                c.insert(consulta25);
                                                //Seleccionamos el id de la etiqueta donde su nombre sea el introducido
                                                String consulta26 = "select id from tags where nom = '" + nomT + "'";
                                                ResultSet rs25 = st25.executeQuery(consulta3);
                                                rs25.next();
                                                int codT = rs25.getInt(1);
                                                System.out.println("Su etiqueta ha sido asignada con éxito!");
                                                rec.crearUnion(c, codT, n1);
                                            }
                                        }while(!nomT.equals("0"));
                                        //Si es distinto a 0 y mayor a 0 entra 
                                    }else if(opcii != 0 && opcii > 0){
                                        Statement st21 = c.getCon().createStatement();
                                        //Sacamos el total de tags que tenga ese id
                                        String consulta23 = "select count(*) from tags where tags.id = '" + opcii + "'";
                                        ResultSet rs23 = st21.executeQuery(consulta23);
                                        rs23.next();

                                        if(rs23.getInt(1) != 0){
                                            //Si es distinto a 0 entra y se coloca
                                            codER[contta] = opcii;
                                            contta++;
                                        }
                                    }
                                    
                                }while(opcii != -1);
                                int[] codERAux = new int[contta];
                                
                                for (int i = 0; i < codERAux.length; i++) {
                                    codERAux[i] = codER[i];
                                    rec.crearUnion(c, codERAux[i], n1);
                                }
                            }
                            sc.nextLine();    
                        break;
                        case "BM":  //Borrar mis recetas
                            if(usu.getRol() != null){ //Volvemos a comprobar que el usuario esté registrado
                                Statement st6 = c.getCon().createStatement();
                                System.out.println("Tus recetas son: "); //Le voy a imrpimir todas las recetas que tienen su nombre de usuario
                                String consulta8 = "select count(*) from recipe where nameusu = '"+usu.getNameusu()+"'"; //Me guardo el numero que tiene para crear un array
                                ResultSet rs8 = st6.executeQuery(consulta8); 
                                rs8.next();
                                int num_recip = rs8.getInt(1);
                                String [] recetasUsu = new String [num_recip]; //Me creo array con el numero de recetas que tiene

                                String consulta7 = "select cod, nombreR from recipe where nameusu = '"+usu.getNameusu()+"'";
                                ResultSet rs7 = st6.executeQuery(consulta7);
                                int count = 0;
                                while(rs7.next()){
                                    recetasUsu[count] = rs7.getString("nombreR"); //Guardo las recetas con la consulta en el array
                                    count++;
                                }

                                for (int i = 0; i < recetasUsu.length; i++) {
                                    System.out.println((i+1)+"- "+recetasUsu[i]); //Le imprimimos la receta
                                }

                                System.out.println("Elige cuál quieres borrar (si no quieres borrar ninguna, pon 0)");
                                int opcionn = sc.nextInt(); //Le hacemos elegir al usuario qué receta quiere borrar mediante la posicion
                                if(opcionn != 0){
                                    opcionn -= 1;
                                    consulta7 = "select cod from recipe where nombreR = '"+recetasUsu[opcionn]+"'"; //Guardamos el codigo que tiene esa receta
                                    ResultSet rs39 = st6.executeQuery(consulta7);
                                    rs39.next();
                                    System.out.println(recetasUsu[opcionn]);
                                    String borrarU = "delete from uniones where cod = "+rs39.getInt(1); //Borramos en base al codigo obtenido primero de uniones para que no de error de constraint
                                    Statement st7 = c.getCon().createStatement();
                                    st7.executeUpdate(borrarU); //Ejecutamos el borrar directamente con update y no queremos devolver nada

                                    String borrarR = "delete from recipe where cod = "+rs39.getInt(1); //Ahora si borramos directamente de recetas
                                    ResultSet rs9 = st7.executeQuery(borrarR);
                                    System.out.println("Elminiando receta...");
                                }
                                sc.nextLine();
                            }
                        break;
                        case "MR": //Modificar receta 
                            if(usu.getRol() != null){
                                Statement st50 = c.getCon().createStatement();
                                
                                String cambio = "";
                                String columna = "";
                                
                                consulta = "select count(*) from recipe"; //Almacenamos el número de recetas totales
                                ResultSet rs50 = st50.executeQuery(consulta);
                                rs50.next();
                                int n50 = rs50.getInt(1);

                                String [] recetas50 = new String [n50]; //Greamos un array con ese numero de recetas

                                String consulta222;
                                consulta222 = "select * from recipe order by cod"; //Seleccionamos todas las recetas ordenadas por codigo
                                ResultSet rs51 = st50.executeQuery(consulta222);

                                cont = 0;
                                System.out.println(" ");
                                while(rs51.next()){
                                    recetas50[cont] = rs51.getString("nombreR");  //Nos guardamos el nombre de cada una de las recetas en el array
                                    cont++;
                                }

                                for (int i = 0; i < recetas50.length; i++){
                                    System.out.println((i+1)+" - "+recetas50[i]); //Se las imprimimos todas al admin
                                }
                                
                                System.out.println("Indica qué receta quieres modificar");
                                int op = sc.nextInt(); //Nos da la opcion que quiere modificar según su posición en el array
                                op -= 1;
                                
                                Statement st12 = c.getCon().createStatement();
                                consulta = "select * from recipe where nombreR = '"+recetas50[op]+"'";
                                ResultSet rs13 = st12.executeQuery(consulta); //Seleccionamos todos los datos de la receta que quiere modificar
                                rs13.next();

                                System.out.println("Nombre de la receta: "+rs13.getString(2));
                                System.out.println("Nombre del usuario: "+rs13.getString(3));
                                System.out.println("Puntuación de la receta: "+rs13.getInt(4));
                                System.out.println("Descripción de la receta: "+rs13.getString(5));
                                System.out.println("Ingredientes: "+rs13.getString(6));
                                System.out.println("Instrucciones: "+rs13.getString(7));
                                
                                sc.nextLine(); //Imprimimos todos los datos para preguntarle qué quiere modificar
                                System.out.println("Introduce qué característica quiéres modificar (nombre receta, nombre usuario, puntuacion, descripcion, ingredientes, instrucciones)");
                                String opp = sc.nextLine();
                                
                                if(opp.equals("nombre receta")){ //Comprobamos que quiere cambiar y directamente lo hacemos 
                                    System.out.println("Introduce el nuevo nombre de receta");
                                    cambio = sc.nextLine();
                                    columna = rs13.getMetaData().getColumnName(2).toLowerCase(); //Nos guardamos el nombre de la columna para poder usar un update en cualquiera de los casos
                                }
                                if(opp.equals("nombre usuario")){
                                    System.out.println("Introduce el nuevo nombre de usuario");
                                    cambio = sc.nextLine().toLowerCase();
                                    columna = rs13.getMetaData().getColumnName(3).toLowerCase();
                                }
                                if(opp.equals("puntuacion")){
                                    System.out.println("Introduce la nueva puntación");
                                    cambio = sc.nextLine().toLowerCase();
                                    columna = rs13.getMetaData().getColumnName(4).toLowerCase();
                                }
                                if(opp.equals("descripcion")){
                                    System.out.println("Introduce la nueva descripción");
                                    cambio = sc.nextLine().toLowerCase();
                                    columna = rs13.getMetaData().getColumnName(5).toLowerCase();
                                }
                                if(opp.equals("ingredientes")){
                                    System.out.println("Introduce los nuevos ingredientes");
                                    cambio = sc.nextLine().toLowerCase();
                                    columna = rs13.getMetaData().getColumnName(6).toLowerCase();
                                }
                                if(opp.equals("instrucciones")){
                                    System.out.println("Introduce las nuevas instrucciones");
                                    cambio = sc.nextLine().toLowerCase();
                                    columna = rs13.getMetaData().getColumnName(7).toLowerCase();
                                }

                                String consulta6 = "update recipe set "+columna+" = '"+cambio+"' where cod = '"+rs13.getInt(1)+"'"; //Directamente podemos hacerlo al obtener el nombre de la columna que quiere cambiar
                                if(st12.executeUpdate(consulta6)!=0)
                                    System.out.println("Modificación correcta");
                            }
                        break;
                        case "USUR": //Le mostramos al admin todos los usuarios del sistema
                            Statement st30 = c.getCon().createStatement();
                            String coonsuultaa = "select count(*) from usuarioR"; //Seleccionamos el numero de usuarios que hay 
                            ResultSet rs30 = st30.executeQuery(coonsuultaa);
                            rs30.next();
                            int nummm = rs30.getInt(1);
                            
                            String [] usuariosT = new String[nummm]; //Array con el numero de posiciones de los usuarios
                            
                            String consuulta = "select nombre, apellidos, email, nameusu, rol from usuarioR"; //Seleccionamos todo menos la contraseña
                            ResultSet rs31 = st30.executeQuery(consuulta);
                            
                            int conttt = 0;
                            while(rs31.next()){ //Nos almacenamos cada uno de ellos en la posición del array correspondiente
                                usuariosT[conttt] = "Nombre: "+rs31.getString(1)+" || Apellidos: "+rs31.getString(2)+" || Email: "+rs31.getString(3)+" || Nombre Usuario: "+rs31.getString(4)+" || Rol: "+rs31.getString(5);
                                conttt++;
                            }
                            System.out.println(" ");
                            System.out.println("Todos los usuarios del sistema son: ");
                            for (int i = 0; i < usuariosT.length; i++) {
                                System.out.println(usuariosT[i]); //Le imprimimos todos los usuarios del sistema
                            }
                            
                        break;    
                        case "BAR": //Borrar Alguna Receta
                            if(usu.getRol() != null){
                                Statement st51 = c.getCon().createStatement();
                                
                                consulta = "select count(*) from recipe"; //Seleccionamos el numero de recetas totales
                                ResultSet rs52 = st51.executeQuery(consulta);
                                rs52.next();
                                int nn = rs52.getInt(1);

                                String [] recetas51 = new String [nn]; //Creamos un array con todas las recetas que hay disponibles

                                String consulta223;
                                consulta223 = "select * from recipe order by cod"; //Seleccionamos todas las recetas
                                ResultSet rs53 = st51.executeQuery(consulta223);
                                cont = 0;
                                
                                System.out.println(" ");
                                while(rs53.next()){
                                    recetas51[cont] = rs53.getString("nombreR");  //Guardamos el nombre de la receta 
                                    cont++;
                                }

                                for (int i = 0; i < recetas51.length; i++){
                                    System.out.println((i+1)+"- "+recetas51[i]); //Imprimimos todas las recetas en el sistema
                                }
                                
                                System.out.println("Elige cuál quieres borrar (si no quieres borrar ninguna, pon 0)");
                                int opcionn = sc.nextInt(); //Elige cuál quiere borrar según su posición en el array
                                if(opcionn != 0){
                                    opcionn -= 1;
                                    System.out.println(recetas51[opcionn]);
                                    consulta223 = "select cod from recipe where nombreR = '"+recetas51[opcionn]+"'"; //Seleccionamos el codigo de la receta que vamos a eliminar
                                    ResultSet rs54 = st51.executeQuery(consulta223);
                                    rs54.next();
                                    
                                    String borrarU = "delete from uniones where cod = "+rs54.getInt(1); //Realizamos la consulta para borrar la receta de uniones y asi evitar errores de constraint
                                    Statement st7 = c.getCon().createStatement();
                                    st7.executeUpdate(borrarU);
                                    
                                    String borrarR = "delete from recipe where cod = "+rs54.getInt(1); //Realizamos la consulta para ahora si borrarla de recetas

                                    st7.executeUpdate(borrarR);
                                    System.out.println("Elminiando receta...");
                                }
                            }
                            sc.nextLine();
                        break;
                        case "C": //Cerrar
                            System.out.println("Saliendo de la app...");
                            num = 2; //Ponemos numero a 2 para que salga del do while
                        break;
                        }
                    }while (!opcion.equals("C"));
                    break;   
                }
            }while(num != 2);
            c.cierre(); //Cerramos la conexion con el sistema o SQL
        }
        public static int[] copiarArray(int[] codE, int[] codEAux) throws ClassNotFoundException, SQLException{ //Método para crear un array auxiliar directamente
            for(int i = 0; i < codEAux.length; i++){
                codEAux[i] = codE[i];
            }
            return codEAux;
        }
}
