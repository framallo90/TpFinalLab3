package com.framallo90.Comprador.Model.Entity;

import com.framallo90.UsuarioAbstracta.Usuario;

/**
 * La clase {@code Comprador} extiende la clase {@code Usuario} y representa un comprador en el sistema.
 * Cada comprador tiene un identificador único, un email y hereda los atributos de la clase {@code Usuario}.
 */
public class Comprador extends Usuario {
    private Integer id;
    private static Integer cont = 0;
    private String email;

    /**
     * Crea una nueva instancia de {@code Comprador} con el nombre, apellido, DNI y email especificados.
     * El identificador único se asigna automáticamente incrementando un contador estático.
     *
     * @param nombre el nombre del comprador
     * @param apellido el apellido del comprador
     * @param dni el documento nacional de identidad del comprador
     * @param email el email del comprador
     */
    public Comprador(String nombre, String apellido, Integer dni, String email) {
        super(nombre, apellido, dni);
        this.id = ++cont;
        this.email = email;
    }

    /**
     * Devuelve una representación en cadena de este comprador.
     *
     * @return una cadena que contiene la información de este comprador
     */
    @Override
    public String toString() {
        return "Comprador{" + super.toString() +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Obtiene el identificador único de este comprador.
     *
     * @return el identificador de este comprador
     */
    public Integer getId() {
        return id;
    }

    /**
     * Obtiene el email de este comprador.
     *
     * @return el email de este comprador
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email de este comprador.
     *
     * @param email el nuevo email de este comprador
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Establece el contador estático que se utiliza para asignar identificadores únicos.
     *
     * @param cont el nuevo valor del contador
     */
    public static void setCont(Integer cont) {
        Comprador.cont = cont;
    }

    /**
     * Obtiene el valor actual del contador estático.
     *
     * @return el valor del contador
     */
    public static Integer getCont() {
        return cont;
    }
}
