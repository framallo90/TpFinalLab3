package com.framallo90.Excepciones;

/**
 * Excepción personalizada lanzada cuando no se encuentra un ID válido.
 * Esta excepción extiende Exception y puede ser utilizada para indicar que
 * un ID específico no se encuentra en la estructura de datos o base de datos esperada.
 *
 * Esta excepción es de tipo verificada, lo que significa que debe ser gestionada explícitamente
 * o declarada en la cláusula throws de un método que pueda lanzarla.
 *
 * @author Framballo
 * @version 1.0
 */
public class InvalidIdNotFound extends Exception {
    private final String message = "El ID ingresado NO se encuentra registrado.";

    public InvalidIdNotFound() {
        super();
    }
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Constructor que permite especificar un mensaje personalizado para la excepción.
     *
     * @param message El mensaje que describe la razón por la cual se lanzó la excepción.
     */
    public InvalidIdNotFound(String message) {
        super(message);
    }
}
