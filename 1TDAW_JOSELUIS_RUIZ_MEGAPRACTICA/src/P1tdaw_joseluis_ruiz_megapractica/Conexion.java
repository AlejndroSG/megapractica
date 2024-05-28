package P1tdaw_joseluis_ruiz_megapractica;

import java.sql.*;

public class Conexion {
    //ATRIBUTOS
    private String url = "jdbc:oracle:thin:@//localhost:1521/xe";
//    private String username = "aula";
    private String username = "usu";
//    private String pass = "aula";
    private String pass = "usu";
    private Connection con;
    
    //CONSTRUCTOR
    public Conexion() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(url, username, pass);
        System.out.println("Conexión con ORACLE establecida.\n");
    }
    
    //GETTER & SETTER
    public Connection getCon(){
        return con;
    }
    
    //MÉTODOS
    public void select(String str) throws SQLException{
        //Método que ejecuta una consulta select a partir de un String.
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(str);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int n_columnas = rsMetaData.getColumnCount();
        
        while(rs.next()){
            for(int i = 1; i <= n_columnas; i++){
                System.out.print(rs.getString(i) + " - ");
            }
            System.out.println("");
        }
    }
    
    public String selectToString(String str) throws SQLException{
        //Método que ejecuta una consulta select a partir de un String y devuelve el resultado como String.
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(str);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int n_columnas = rsMetaData.getColumnCount();
        
        String out = "";
        
        while(rs.next()){
            for (int i = 1; i <= n_columnas; i++) {
                out += rs.getString(i) + " - ";
            }
            out += "\n";
        }
        return out;
    }
    
    public void insert(String str) throws SQLException{
        //Método que ejecuta un insert a partir de un String.
        Statement st = con.createStatement();
        System.out.println("Insertando...");
        st.executeUpdate(str);
    }
    
    public void cierre() throws SQLException{
        //Método que cierra la conexión con la base de datos.
        con.close();
    }
}
