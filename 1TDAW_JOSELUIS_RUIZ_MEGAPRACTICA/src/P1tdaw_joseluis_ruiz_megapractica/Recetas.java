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
    public void imprimeTags(){
        
    }
    
    public void crearUnion(){
        
    }
}
