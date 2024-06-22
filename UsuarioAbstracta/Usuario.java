package com.framallo90.UsuarioAbstracta;

import java.util.Objects;

/**
 * Clase abstracta que representa a un usuario genérico con nombre, apellido y número de documento.
 * Proporciona métodos para acceder y modificar estos atributos, así como métodos para comparar
 * y obtener una representación textual del objeto.
 *
 * Ejemplo de uso:
 * ```
 * Usuario usuario = new Cliente("Juan", "Pérez", 12345678);
 * System.out.println(usuario.toString());
 * ```
 *
 * @author Framallo
 * @version 1.0
 */
public abstract class Usuario {
    private String nombre;
    private String apellido;
    private Integer dni;

    /**
     * Constructor que inicializa un usuario con nombre, apellido y número de documento.
     *
     * @param nombre   El nombre del usuario.
     * @param apellido El apellido del usuario.
     * @param dni      El número de documento del usuario.
     */
    public Usuario(String nombre, String apellido, Integer dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    /**
     * Retorna una representación en cadena de caracteres del objeto Usuario.
     *
     * @return Una cadena que representa el objeto Usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                '}';
    }

    /**
     * Retorna el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el apellido del usuario.
     *
     * @return El apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param apellido El nuevo apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Retorna el número de documento del usuario.
     *
     * @return El número de documento del usuario.
     */
    public Integer getDni() {
        return dni;
    }

    /**
     * Establece el número de documento del usuario.
     *
     * @param dni El nuevo número de documento del usuario.
     */
    public void setDni(Integer dni) {
        this.dni = dni;
    }

    /**
     * Compara este objeto con otro para determinar si son iguales.
     * Dos usuarios son iguales si tienen el mismo número de documento.
     *
     * @param o El objeto a comparar con este Usuario.
     * @return true si los objetos son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni, usuario.dni);
    }

    /**
     * Retorna el valor hash del objeto Usuario basado en su número de documento.
     *
     * @return El valor hash del objeto Usuario.
     */
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
