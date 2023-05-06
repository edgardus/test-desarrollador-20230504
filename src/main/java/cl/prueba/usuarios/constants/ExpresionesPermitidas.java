package cl.prueba.usuarios.constants;

/**
 * Clase encargada de manejar las constantes para expresiones regulares
 * de las validaciones de la Aplicación.
 */
public class ExpresionesPermitidas {
    public static final String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String INVALID_PASSWORD = "(^([^A-Z]*|[^a-z]*|[^0-9]*.{0,1}))$";
    public static final String INVALID_PASSWORD_MESSAGE = "Contraseña debe contener: Una mayúscula, letras minúsculas, y dos números.";


}
