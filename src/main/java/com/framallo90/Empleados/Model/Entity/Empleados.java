/**
 * Esta clase representa a un empleado dentro de un sistema, heredando propiedades de la clase `Usuario`.
 * Un objeto `Empleados` contiene información sobre un empleado, incluyendo su desempeño en ventas, credenciales de inicio de sesión y tipo de empleado.
 *
 * @see Usuario
 */
package com.framallo90.Empleados.Model.Entity;

import com.framallo90.UsuarioAbstracta.Usuario;

public class Empleados extends Usuario {
    /**
     * Identificador único asignado al empleado al crearse. Este valor se genera automáticamente y se incrementa.
     */
    private final Integer id;

    /**
     * Número de autos vendidos por el empleado.
     */
    private Integer autosvendidos;

    /**
     * Nombre de usuario para las credenciales de inicio de sesión del empleado.
     */
    private String username;

    /**
     * Contraseña para las credenciales de inicio de sesión del empleado.
     */
    private String password;

    /**
     * Tipo de empleado dentro de la empresa (por ejemplo, "Vendedor", "Gerente").
     */
    private String tipo;

    /**
     * Contador interno utilizado para generar identificadores únicos. Esta variable no debe modificarse directamente.
     */
    private static Integer cont = 0;

    /**
     * Constructor para la clase `Empleados`.
     *
     * @param nombre   Primer nombre del empleado.
     * @param apellido Apellido del empleado.
     * @param dni       Número de identificación del empleado (DNI en este caso).
     * @param autosvendidos Número de autos vendidos por el empleado.
     * @param username  Nombre de usuario para las credenciales de inicio de sesión del empleado.
     * @param password  Contraseña para las credenciales de inicio de sesión del empleado.
     * @param tipo      Tipo de empleado dentro de la empresa (por ejemplo, "Vendedor", "Gerente").
     */
    public Empleados(String nombre, String apellido, Integer dni, Integer autosvendidos, String username, String password, String tipo) {
        super(nombre, apellido, dni);
        this.id = ++cont;
        this.autosvendidos = autosvendidos;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }



    /**
     * Método getter para obtener el ID único del empleado.
     *
     * @return El ID único del empleado.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método getter para obtener el número de autos vendidos por el empleado.
     *
     * @return El número de autos vendidos por el empleado.
     */
    public Integer getAutosvendidos() {
        return autosvendidos;
    }

    /**
     * Método setter para actualizar el número de autos vendidos por el empleado.
     *
     * @param autosvendidos El nuevo número de autos vendidos por el empleado.
     */
    public void setAutosvendidos(Integer autosvendidos) {
        this.autosvendidos = autosvendidos;
    }

    /**
     * Método getter para obtener el nombre de usuario del empleado para el inicio de sesión.
     *
     * @return El nombre de usuario del empleado.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Método setter para actualizar el nombre de usuario del empleado para el inicio de sesión.
     *
     * @param username El nuevo nombre de usuario para el empleado.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Método getter para obtener la contraseña del empleado para el inicio de sesión.
     *
     * @return La contraseña del empleado (nota: no se recomienda almacenar contraseñas en texto plano).
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método setter para actualizar la contraseña del empleado para el inicio de sesión.
     *
     * @param password La nueva contraseña para el empleado (nota: no se recomienda almacenar contraseñas en texto plano).
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Método setter para actualizar el tipo de empleado dentro de la empresa.
     *
     * @param tipo El nuevo tipo de empleado (por ejemplo, "Vendedor", "Gerente").
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método getter para obtener el contador interno utilizado para generar identificadores únicos.
     * Este método no debe usarse directamente para modificar el valor del contador.
     *
     * @return El valor del contador interno.
     */
    public static Integer getCont() {
        return cont;
    }

    /**
     * Método setter para actualizar el valor del contador interno utilizado para generar identificadores únicos.
     * Este método debe usarse con precaución, ya que puede afectar la generación de ID únicos.
     *
     * @param cont El nuevo valor del contador interno.
     */
    public static void setCont(Integer cont) {
        Empleados.cont = cont;
    }

    public String getTipo() {
        return tipo;
    }


}