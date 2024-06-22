package com.framallo90.Excepciones;

/**
 * Excepción personalizada que representa el caso en el que se intenta acceder a un stock vacío.
 * Esta excepción extiende Exception y puede ser lanzada cuando se detecta un intento de
 * acceso a un stock que no contiene elementos disponibles.
 *
 * Esta excepción es de tipo verificada, lo que significa que debe ser gestionada explícitamente
 * o declarada en la cláusula throws de un método que pueda lanzarla.
 *
 * @author Framballo
 * @version 1.0
 */
public class EmptyAStockException extends Exception {

    /**
     * Constructor que permite especificar un mensaje personalizado para la excepción.
     *
     * @param message El mensaje que describe la razón por la cual se lanzó la excepción.
     */
    public EmptyAStockException(String message) {
        super(message);
    }
}
