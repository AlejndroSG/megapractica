package P1tdaw_joseluis_ruiz_megapractica;

import java.sql.*;

public class Conexion {
    //ATRIBUTOS
    private String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    private String username = "aula";
    private String pass = "aula";
    private Connection con;
    
    //CONSTRUCTOR
    public Conexion() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(url, username, pass);
        System.out.println("Conexión con ORACLE establecida.\n");
    }
    
    //MÉTODOS
    public void select
}
