package P1tdaw_joseluis_ruiz_megapractica;

import java.sql.SQLException;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner (System.in);
        Conexion c = new Conexion();
        System.out.println("Bienvenido a recetasPAL");
        System.out.println("¿Estás registrado?");
        char opc;
        do{
            opc = sc.next().toUpperCase().charAt(0);
        }while(opc != 'S' && opc != 'N');
        
        if(opc == 'S'){
            System.out.println("Introduce nombre de usuario");
            String nom_usu = sc.nextLine();
            System.out.println("Introduce la contraseña");
            String contr = sc.nextLine();
            
            ResulSet rs = c.select("nameusu, password from usuarioR where password ="+contr+" and nameusu ="+nom_usu);
        }
    }   
    
    public static void totalRecetas(){
        
    }
}
