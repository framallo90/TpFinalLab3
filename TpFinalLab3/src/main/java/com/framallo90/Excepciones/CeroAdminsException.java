package com.framallo90.Excepciones;

public class CeroAdminsException extends Exception{
    private final String message = "Ha ocurrido un error. No puede eliminarse el único administrador existente en el sistema.";

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CeroAdminsException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
