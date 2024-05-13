package P1tdaw_joseluis_ruiz_megapractica;

import java.sql.*;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner (System.in);
        Conexion c = new Conexion();
        String consulta1 = "";
        System.out.println("Bienvenido a recetasPAL");
        System.out.println("¿Estás registrado?(S/N)");
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
            consulta1 = "select nameusu, password from usuarioR where password = '" + contr + "' and nameusu = '" + nom_usu + "'";
            c.select(consulta1);
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
                
                if(count < 1){
                    System.out.println("Tu cuenta no existe , vamos a crear una!!");
                    System.out.println("Introduce el nombre");
                    String nom = sc.nextLine();
                    System.out.println("Introduce el apellido");
                    String apell = sc.nextLine();
                    System.out.println("Introduce el correo electrónico");
                    String corr = sc.nextLine();
                    String consulta = "insert into usuarioR values ('"+ nom + "', '"+ apell + "', '"+ corr +"', '"+ nom_usu +"', 'usuarioR', '"+ contr +"'";
                    c.insert(consulta);
                }
                
                c.cierre();
            }
        }
    }   
    
    public static void totalRecetas(){
        
    }
}
