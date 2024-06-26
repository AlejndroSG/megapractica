package P1tdaw_joseluis_ruiz_megapractica;

import java.sql.*;
import java.util.*;

public class Recetas extends Usuarios{
    //ATRIBUTOS
    private String nombreR;
    private float punt;
    private String descripcion;
    private String ingredientes;
    private String instruccionesR;
    
    //CONSTRUCTOR
    public Recetas(){
        super("","","");
        nombreR = "";
        punt = 0;
        descripcion = "";
        ingredientes = "";
        instruccionesR = "";
    }
    public Recetas(String nombreR, float punt, String descripcion, String ingredientes, String instruccionesR, String nameusu, String password, String rol) {
        super(nameusu, password, rol);
        this.nombreR = nombreR;
        this.punt = punt;
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
    public void imprimeTags() throws ClassNotFoundException, SQLException{ //Método que imprime todas las etiquetas disponibles para cada receta
        Scanner sc = new Scanner (System.in);
        Conexion c = new Conexion();
        Statement st = c.getCon().createStatement();
        
        String consulta = "select count(*) from tags"; //Seleccionamos cuantas etiquetas hay
        ResultSet rs = st.executeQuery(consulta);
        rs.next();
        int n = rs.getInt(1);
        String consulta1 = "select nom from tags order by id"; //Seleccionamos el nombre de las etiquetas ordenado por el id
        ResultSet rs2 = st.executeQuery(consulta1);
        rs2.next();
        
        String[] tags = new String[n]; //Nos creamos el array con el numero de posiciones de las etiquetas que hay totales
        for (int i = 0; i < n; i++) {
            tags[i] = rs2.getString("nom"); //Almacenamos el nombre de la etiqueta y se la imprimimos
            System.out.println((i+1) + " " + tags[i]);
            rs2.next();
        }
    }
    
    public void crearUnion(Conexion c, int codT, int codR) throws ClassNotFoundException, SQLException{ //Creamos la union de la etiqueta con la propia receta que elija el usuario
        String consulta = "insert into uniones values ("+ codT + ", " + codR + ")";
        c.insert(consulta); //Realizamos el insert
    }
}
