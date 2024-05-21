package P1tdaw_joseluis_ruiz_megapractica;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner (System.in);
        Conexion c = new Conexion();
        String consulta1 = "";
        System.out.println("Bienvenido a recetasPANAL");
        System.out.println("¿Estás registrado?(S/N)");
        Usuarios usu = new Usuarios();
        char opc;
        do{
            opc = sc.next().toUpperCase().charAt(0);
        }while(opc != 'S' && opc != 'N');
        
        if(opc == 'S'){
            sc.nextLine();
            System.out.println("Introduce nombre de usuario");
            String nom_usu = sc.nextLine().toLowerCase();
            System.out.println("Introduce la contraseña");
            String contr = sc.nextLine();
            Statement st = c.getCon().createStatement();
            consulta1 = "select count(*) from usuarioR where password = '" + contr + "' and nameusu = '" + nom_usu + "'";
            ResultSet rs = st.executeQuery(consulta1);
            rs.next();
            int num = rs.getInt(1);
            if(num == 1){
                System.out.println("Se ha iniciado sesión con éxito");
                String consulta = "select rol from usuarioR where password = '" + contr + "' and nameusu = '" + nom_usu + "'";
                rs = st.executeQuery(consulta);
                rs.next();
                String rol = rs.getString(1);
                usu = new Usuarios(nom_usu, contr, rol);
                System.out.println("Usuario creado exitosamente en Java");
            }else{
                System.out.println("No existe el usuario, ¿quieres crear uno nuevo?(S/N)");
                char opcion = sc.next().toUpperCase().charAt(0);
                if(opcion == 'S'){
                    sc.nextLine();
                    System.out.println("Introduce el nombre");
                    String nom = sc.nextLine();
                    System.out.println("Introduce el apellido");
                    String apell = sc.nextLine();
                    System.out.println("Introduce el correo electrónico");
                    String corr = sc.nextLine();
                    String consulta = "insert into usuarioR values ('"+ nom + "', '"+ apell + "', '"+ corr +"', '"+ nom_usu +"', 'usuarioR', '"+ contr +"')";
                    c.insert(consulta);
                    usu = new Usuarios(nom_usu, contr, "usuarioR");
                }else{
                    System.out.println("No has iniciado sesión, por lo que no tendrás acceso total al programa");
                }
            }
        }else if(opc == 'N'){
            System.out.println("¿Quiéres registrarte?(S/N)");
            do{
                opc = sc.next().toUpperCase().charAt(0);
            }while(opc != 'S' && opc != 'N');
            if(opc == 'S'){
                sc.nextLine();
                System.out.println("Introduce nombre de usuario");
                String nom_usu = sc.nextLine().toLowerCase();
                System.out.println("Introduce la contraseña");
                String contr = sc.nextLine();
                
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
            }else{
                System.out.println("No has iniciado sesión, por lo que no tendrás acceso total al programa");
            }
        }
        /*Statement st2 = c.getCon().createStatement();
        String consulta3;
        consulta3 = "select count(*) from usuarioR";
        ResultSet rs2 = st2.executeQuery(consulta3);
        rs2.next();
        int n = rs2.getInt(1);
        
        String [] usuarios = new String [n];

        String consulta2;
        consulta2 = "select * from usuarioR";
        ResultSet rs = st2.executeQuery(consulta2);

        int cont = 0;

        while(rs.next()){
            usuarios[cont] = rs.getString("nameusu");
            cont++;
        }
        
        for (int i = 0; i < usuarios.length; i++) {
            System.out.println(usuarios[i]);
        }*/
        Recetas r = new Recetas();
        int selecCod;
        int num;
        String opcion;
        System.out.println(" ");
        do{
            do{
                System.out.println("Entrar a la aplicación (1)");
                System.out.println("Salir de la aplicación (2)");
                num = sc.nextInt();
            }while(num != 1 && num != 2);
            sc.nextLine();
            switch(num){
                case 1:
                    do{
                    System.out.println("\nMenú de recetasPANAL");
                    System.out.println("-----------------------------------------------");
                    System.out.println("Buscar recetas (BR)");
                    System.out.println("Ver todas las recetas (VT)");
                    if(usu.getRol() != null){
                        System.out.println("Ver Perfil (VP)");
                        System.out.println("Modificar Perfil (MP)");
                        System.out.println("Ver mis recetas (VR)");
                        System.out.println("Añadir recetas (AR)");
                        if(usu.getRol().equals("usuarioR")){
                            System.out.println("Borrar mis recetas (BM)");
                        }
                        if(usu.getRol().equals("admin")){
                            System.out.println("Modificar recetas (MR)");
                            System.out.println("Ver todos los usuarios registrados (USUR)");
                            System.out.println("Borrar alguna recetas (BAR)");
                        }
                    }
                    System.out.println("Cerrar (C)");
                    System.out.println("Elige la opcion giipollas");
                    opcion = sc.nextLine().toUpperCase();
                    String opci;
                    int opcn;
                    int[] codE;
                    
                    switch(opcion){
                        case "BR": //Esto se hace en la 3ª parte
                            do{
                                System.out.println("¿Mediante que método quieres buscar, Nombre de la receta(NR), Etiqueta(E) o Nombre de Usuario(NU)?");
                                opci = sc.nextLine().toUpperCase();
                                System.out.println("");
                            }while(!opci.equals("NR") && !opci.equals("E") && !opci.equals("NU"));
                            
                                switch(opci){
                                    case "NR":
                                        System.out.println("Introduce el nombre o una palabra clave del nombre de la receta.");
                                        String palabC = sc.nextLine().toLowerCase();
                                        
                                        Statement st20 = c.getCon().createStatement();
                                        String consulta10 = "select count(*) from recipe where lower(nombreR) like '%" + palabC + "%'";
                                        ResultSet rs20 = st20.executeQuery(consulta10);
                                        rs20.next();
                                        String[] vReTo = new String[rs20.getInt(1)];
                                        String consulta20 = "select cod, nombreR from recipe where lower(nombreR) like '%" + palabC + "%'";
                                        ResultSet rs21 = st20.executeQuery(consulta20);
                                        for(int i = 0; i < vReTo.length; i++) {
                                            rs21.next();
                                            vReTo[i] = rs21.getInt(1) + " - " + rs21.getString(2);
                                            System.out.println(vReTo[i]);
                                        }                                      
                                        
                                        System.out.println("Selecciona el código de la receta que quieres seleccionar.");
                                        selecCod = sc.nextInt();
                                        
                                        String consulta22 = "select * from recipe where cod = '" + selecCod + "'";
                                        ResultSet rs22 = st20.executeQuery(consulta22);
                                        rs22.next();
                                        
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
                                        Statement st21 = c.getCon().createStatement();
                                        r.imprimeTags();
                                        
                                        System.out.println("");
                                        int contt = 0;
                                        do{
                                            System.out.println("Introduce el código de la etiqueta que quieres buscar, si no quieres buscar ninguna más introduce (-1)");
                                            opcn = sc.nextInt();
                                            contt++;
                                            codE = new int[contt];
                                            codE[contt - 1] = opcn;
                                        }while(opcn != -1);
                                        
                                        for (int i = 0; i < codE.length; i++) {
                                            
                                        }
                                        
                                        System.out.println("");
                                        String consulta23 = "select count(*) from recipe, tags, uniones where recipe.cod = uniones.cod and tags.id = uniones.id and tags.id = '" + opcn + "'";
                                        ResultSet rs23 = st21.executeQuery(consulta23);
                                        rs23.next();
                                        String[] vReEt = new String[rs23.getInt(1)];
                                        String consulta24 = "select recipe.cod, nombreR from recipe, tags, uniones where recipe.cod = uniones.cod and tags.id = uniones.id and tags.id = '" + opcn + "'";
                                        ResultSet rs24 = st21.executeQuery(consulta24);
                                        for(int i = 0; i < vReEt.length; i++) {
                                            rs24.next();
                                            vReEt[i] = rs24.getInt(1) + " - " + rs24.getString(2);
                                            System.out.println(vReEt[i]);
                                        }
                                        
                                        System.out.println("");
                                        System.out.println("Selecciona el código de la receta que quieres seleccionar.");
                                        selecCod = sc.nextInt();
                                        
                                        String consulta25 = "select * from recipe where cod = '" + selecCod + "'";
                                        ResultSet rs25 = st21.executeQuery(consulta25);
                                        rs25.next();
                                        
                                        System.out.println("");
                                        System.out.println("Código: "+rs25.getInt(1));
                                        System.out.println("Nombre de la receta: "+rs25.getString(2));
                                        System.out.println("Nombre del usuario: "+rs25.getString(3));
                                        System.out.println("Puntuación de la receta: "+rs25.getInt(4));
                                        System.out.println("Descripción de la receta: "+rs25.getString(5));
                                        System.out.println("Ingredientes: "+rs25.getString(6));
                                        System.out.println("Instrucciones: "+rs25.getString(7));
                                        
                                        sc.nextLine();
                                    break;
                                    case "NU":
                                        Statement st22 = c.getCon().createStatement();
                                        
                                        System.out.println("Introduce el usuario del que quieres buscar sus recetas.");
                                        String usuR = sc.nextLine();
                                        System.out.println(""); 
                                        
                                        String consulta26 = "select count(*) from recipe where nameusu = '" + usuR + "'";
                                        ResultSet rs26 = st22.executeQuery(consulta26);
                                        rs26.next();
                                        
                                        if(rs26.getInt(1) == 0){
                                            System.out.println("No hay resultados disponibles.");
                                        }else{
                                            String[] vReUs = new String[rs26.getInt(1)];
                                            String consulta27 = "select recipe.cod, nombreR from recipe where nameusu = '" + usuR + "'";
                                            ResultSet rs27 = st22.executeQuery(consulta27);
                                            for(int i = 0; i < vReUs.length; i++) {
                                                rs27.next();
                                                vReUs[i] = rs27.getInt(1) + " - " + rs27.getString(2);
                                                System.out.println(vReUs[i]);
                                            }

                                            System.out.println("");
                                            System.out.println("Selecciona el código de la receta que quieres seleccionar.");
                                            selecCod = sc.nextInt();

                                            String consulta28 = "select * from recipe where cod = '" + selecCod + "'";
                                            ResultSet rs28 = st22.executeQuery(consulta28);
                                            rs28.next();

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
                            String consulta;
                            consulta = "select count(*) from recipe";
                            ResultSet rs = st.executeQuery(consulta);
                            rs.next();
                            int n = rs.getInt(1);

                            String [] recetas = new String [n];

                            String consulta2;
                            consulta2 = "select * from recipe order by cod";
                            ResultSet rs2 = st.executeQuery(consulta2);

                            int cont = 0;
                            System.out.println(" ");
                            while(rs2.next()){
                                recetas[cont] = rs2.getString("nombreR"); 
                                cont++;
                            }
                            
                            for (int i = 0; i < recetas.length; i++){
                                System.out.println(recetas[i]);
                            }
                        break;
                        case "VP":
                            System.out.println("-------------------------------------------------------------");
                            System.out.println(" ");
                            Statement st3 = c.getCon().createStatement();
                            consulta = "";
                            consulta = "select nombre, apellidos, email from usuarioR where nameusu = '"+usu.getNameusu()+"'";
                            ResultSet rs4 = st3.executeQuery(consulta);
                            rs4.next();
                            System.out.println("Datos de tu perfil");
                            System.out.println("Nombre: "+rs4.getString(1));
                            System.out.println("Apellidos: "+rs4.getString(2));
                            System.out.println("Email: "+rs4.getString(3));
                            System.out.println("Nombre de usuario: "+usu.getNameusu());
                            System.out.println("Rol en el sistema: "+usu.getRol());
                        break;
                        case "VR":
                            System.out.println("Tus recetas son: ");
                            Statement st8 = c.getCon().createStatement();
                            String consulta5 = "select count(*) from recipe where nameusu = '"+usu.getNameusu()+"'";
                            ResultSet rs10 = st8.executeQuery(consulta5);
                            rs10.next();
                            int number = rs10.getInt(1);
                            String [] misrecetas = new String [number];
                            
                            String consulta11 = "select cod, nombreR from recipe where nameusu = '"+usu.getNameusu()+"'";
                            ResultSet rs11 = st8.executeQuery(consulta11);
                            
                            cont = 0;
                            int contador = 1;
                            while(rs11.next()){
                                misrecetas[cont] = rs11.getString(2);
                                cont++;
                                
                                System.out.println(rs11.getInt(1) + ".- "+ rs11.getString(2));
                            }

                            
                            int numm = -10;
                            do{
                                System.out.println("\nIntroduce cuál de las tres quieres ver la información, o pon 0 si quieres salir del programa");
                                do{
                                    numm = sc.nextInt();
                                }while(numm < 0 && numm > cont);
                                if(numm != 0){
                                    System.out.println(" ");
                                    String consulta12 = "select * from recipe where cod = '"+numm+"'";
                                    ResultSet rs12 = st8.executeQuery(consulta12);
                                    rs12.next();
                                    System.out.println("Código: "+rs12.getInt(1));
                                    System.out.println("Nombre de la receta: "+rs12.getString(2));
                                    System.out.println("Nombre del usuario: "+rs12.getString(3));
                                    System.out.println("Puntuación de la receta: "+rs12.getInt(4));
                                    System.out.println("Descripción de la receta: "+rs12.getString(5));
                                    System.out.println("Ingredientes: "+rs12.getString(6));
                                    System.out.println("Instrucciones: "+rs12.getString(7));
                                }
                            }while (numm != 0);
                            sc.nextLine();
                        break;
                        case "AR":
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
                            Recetas rec = new Recetas(nameR, punt, descrip, ingred, pasos, usu.getNameusu(), usu.getPassword(), usu.getRol());
                            String consulta3 = "insert into recipe (nombreR, nameusu, puntuacion, descripcion, ingredientes, instruccionesR) values ('"+nameR+"' , '"+usu.getNameusu()+"' , '"+punt+"' , '"+descrip+"' , '"+ingred+"' , '"+pasos+"')";
                            c.insert(consulta3);
                            System.out.println("Insertando receta...");
                            Statement st4 = c.getCon().createStatement();
                            String consulta4 = "select cod from recipe where nombreR = '"+nameR+"'";
                            ResultSet rs5 = st4.executeQuery(consulta4);
                            rs5.next();
                            int n1 = rs5.getInt(1);
                            rec.imprimeTags();
                            
                            int opcii;
                            do{
                                System.out.println("Introduce la posición de las etiquetas que quieres asignar a tu receta, si quieres añadir una nueva introduce (0), si quieres salir introduce (-1).");
                                opcii = sc.nextInt(); 

                                String nomT = "";
                                if(opcii == 0){
                                    do{
                                        sc.nextLine();
                                        System.out.println("Introduce el nombre de la nueva etiqueta, si no quieres introducir otra introduce (0).");
                                        nomT = sc.nextLine();
                                        if(!nomT.equals("0")){
                                            Statement st25 = c.getCon().createStatement();

                                            String consulta25 = "insert into tags (nom) values ('" + nomT + "')";
                                            c.insert(consulta25);
                                            String consulta26 = "select id from tags where nom = '" + nomT + "'";
                                            ResultSet rs25 = st25.executeQuery(consulta3);
                                            rs25.next();
                                            int codT = rs25.getInt(1);
                                            System.out.println("Su etiqueta ha sido asignada con éxito!");
                                            rec.crearUnion(c, codT, n1);
                                        }
                                    }while(!nomT.equals("0"));
                                }else if(opc == -1){

                                }
                            }while(opc != -1);
                                                        
                        break;
                        case "BM":
                            Statement st6 = c.getCon().createStatement();
                            System.out.println("Tus recetas son: ");
                            String consulta8 = "select count(*) from recipe where nameusu = '"+usu.getNameusu()+"'";
                            ResultSet rs8 = st6.executeQuery(consulta8);
                            rs8.next();
                            int num_recip = rs8.getInt(1);
                            String [] recetasUsu = new String [num_recip];
                            
                            String consulta7 = "select nombreR from recipe where nameusu = '"+usu.getNameusu()+"'";
                            ResultSet rs7 = st6.executeQuery(consulta7);
                            rs7.next();
                            int count = 0;
                            while(rs7.next()){
                                recetasUsu[count] = rs7.getString("nombreR");
                                count++;
                            }
                            
                            for (int i = 0; i < recetasUsu.length; i++) {
                                System.out.println((i+1) +" "+ recetasUsu[i]);
                            }
                            
                            System.out.println("Elige cuál quieres borrar");
                            int opcionn = sc.nextInt();
                            opcionn -= 1;
                            System.out.println(recetasUsu[opcionn]);
                            String recetaB = recetasUsu[opcionn];
                            String borrarR = "delete from recipe where nombreR = '"+recetaB+"'";
                            Statement st7 = c.getCon().createStatement();
                            
                            ResultSet rs9 = st7.executeQuery(borrarR);
                            System.out.println("Elminiando receta...");
                        break;
                        case "BAR":
                            Statement st5 = c.getCon().createStatement();
                            System.out.println("Introduce el nombre de la receta que quieres eliminar");
                            String nomR = sc.nextLine();
                            String borrar = "delete from recipe where nombreR = '"+nomR+"'";
                            ResultSet rs6 = st5.executeQuery(borrar);
                            System.out.println("Eliminando receta...");
                        break;
                        case "C":
                            System.out.println("Saliendo de la app...");
                            num = 2;
                        break;
                    }

                    }while (!opcion.equals("C"));
                break;
            }
        }while(num != 2);
        
        c.cierre();

    }   
    
    public static void totalRecetas(){
        
    }
}
