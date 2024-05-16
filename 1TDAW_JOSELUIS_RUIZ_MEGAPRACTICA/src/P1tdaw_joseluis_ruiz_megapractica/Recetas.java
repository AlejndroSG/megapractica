package P1tdaw_joseluis_ruiz_megapractica;

import java.sql.*;
import java.util.*;

public class Recetas extends Usuarios{
    //ATRIBUTOS
    private String nombreR;
    private String descripcion;
    private String ingredientes;
    private String instruccionesR;
    
    //CONSTRUCTOR
    public Recetas(){
        super("","","");
        nombreR = "";
        descripcion = "";
        ingredientes = "";
        instruccionesR = "";
    }
    public Recetas(String nombreR, String descripcion, String ingredientes, String instruccionesR, String nameusu, String password, String rol) {
        super(nameusu, password, rol);
        this.nombreR = nombreR;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.instruccionesR = instruccionesR;
    }
    
    //GETTER & SETTER
    public String getNombreR() {
        return nombreR;
    }
    public void setNombreR(String nombreR) {
        this.nombreR = nombreR;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getInstruccionesR() {
        return instruccionesR;
    }
    public void setInstruccionesR(String instruccionesR) {
        this.instruccionesR = instruccionesR;
    }
    
    //MÉTODOS
    public void imprimeTags() throws ClassNotFoundException, SQLException{
        Scanner sc = new Scanner (System.in);
        Conexion c = new Conexion();
        Statement st = c.getCon().createStatement();
        
        String consulta = "select count(*) from tags";
        ResultSet rs = st.executeQuery(consulta);
        rs.next();
        int n = rs.getInt(1);
        String consulta1 = "select nom from tags order by id";
        ResultSet rs2 = st.executeQuery(consulta1);
        rs2.next();
        
        String[] tags = new String[n];
        for (int i = 0; i < n; i++) {
            tags[i] = rs2.getString("nom");
            System.out.println((i+1) + " " + tags[i]);
            rs2.next();
        }
        
        System.out.println("Introduce la posición de las etiquetas que quieres asignar a tu receta, si quieres añadir una nueva introduce (0), si quieres salir introduce (-1).");
        int opc = sc.nextInt(); 
        
        String nomT = "";
        if(opc == 0){
            do{
                sc.nextLine();
                System.out.println("Introduce el nombre de la nueva etiqueta, si no quieres introducir otra introduce (0).");
                nomT = sc.nextLine();
                if(!nomT.equals("0")){
                    String consulta2 = "insert into tags (nom) values ('" + nomT + "')";
                    c.insert(consulta2);
                    System.out.println("Su etiqueta ha sido asignada con éxito!");
                } 
            }while(!nomT.equals("0"));
        }else if(opc == -1){
            
        }
    }
    
    public void crearUnion(){
        
    }
}
