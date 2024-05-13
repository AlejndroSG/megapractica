package P1tdaw_joseluis_ruiz_megapractica;

import java.sql.*;
import java.util.*;
public class Usuarios {
    private String nameusu;
    private String password;
    private String rol;
    
    //Constructor
    public Usuarios(String nameusu, String password, String rol) {
        this.nameusu = nameusu;
        this.password = password;
        this.rol = rol;
    }
    
    //Getter & Setter
    public String getNameusu() {
        return nameusu;
    }

    public void setNameusu(String nameusu) {
        this.nameusu = nameusu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    //Metodos
    
}
