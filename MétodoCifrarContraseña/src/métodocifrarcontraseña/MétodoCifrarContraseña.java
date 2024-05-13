package métodocifrarcontraseña;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MétodoCifrarContraseña {

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    /**
     * Cifra con el algoritmo SHA-256 la contraseña proporcionada. Dicho algoritmo es irreversible, haciendo que sea útil
     * a la hora de almacenar contraseñas de usuarios para una página web.
     * @param contrasena Contraseña
     * @return String
     */
    public static String cifrarContrasena(String contrasena){
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
        }
        
        return null;
    }
}
