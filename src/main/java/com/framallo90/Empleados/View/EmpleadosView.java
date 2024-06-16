/**
 * Clase que proporciona métodos para la interacción con el usuario para la gestión de empleados.
 *
 * @author Framballo90
 * @since v1.0
 */
package com.framallo90.Empleados.View;

import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.consola.Consola;

import java.awt.*;
import java.util.List;

public class EmpleadosView {

    /**
     * Genera un nuevo objeto Empleados a partir de la información ingresada por el usuario.
     *
     * @return Un nuevo objeto Empleados con los datos ingresados.
     */
    public Empleados generarEmpleado() {
        String nombre, apellido, username, password, tipo;
        Integer dni;

        nombre = Consola.ingresarXString("nombre");
        apellido = Consola.ingresarXString("apellido");
        dni = Consola.ingresarXInteger("dni");
        Consola.limpiarBuffer();
        username = Consola.ingresarXString("username");
        password = Consola.ingresarXString("password");
        tipo = this.generarTipo();

        return new Empleados(nombre, apellido, dni, 0, username, password, tipo);
    }

    /**
     * Permite al usuario seleccionar el tipo de empleado (vendedor o administrador) y lo devuelve como cadena.
     *
     * @return El tipo de empleado seleccionado por el usuario.
     */
    public String generarTipo() {
        String tipo;

        while (true) {
            tipo = Consola.ingresarXString("""
                    \n  tipo de usuario:
                    vendedor
                    administrador\n""");

            if (tipo.equalsIgnoreCase("administrador") ||
                    tipo.equalsIgnoreCase("vendedor")) {
                return tipo;
            } else {
                System.out.println("ingresar un dato válido");
            }
        }
    }

    //DOCUMENTAR
    public void muestroEmpleado(Empleados empleados){
        Consola.soutString(empleados.toString());
    }

    public void muestroEmpleados(List<Empleados> empleadosList){
        for (Empleados empleados : empleadosList)
            Consola.soutString(empleados.toString());
    }

    //DOCUMENTAR
    public void printMenuAdministrador(){
        System.out.println("""
                1.""");
    }
}