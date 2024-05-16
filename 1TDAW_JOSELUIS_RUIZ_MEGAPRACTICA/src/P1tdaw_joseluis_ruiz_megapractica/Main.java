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
                        if(usu.getRol().equals("admin")){
                            System.out.println("Modificar recetas (MR)");
                            System.out.println("Ver todos los usuarios registrados (USUR)");
                        }
                    }
                    System.out.println("Cerrar (C)");
                    System.out.println("Elige la opcion giipollas");
                    opcion = sc.nextLine().toUpperCase();

                    switch(opcion){
                        case "BR": //Esto se hace en la 3ª parte
                            
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
                            consulta2 = "select * from recipe";
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
                            System.out.println("Datos de tu perfil");
                            System.out.println("Nombre de usuario: "+usu.getNameusu());
                            System.out.println("");
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
