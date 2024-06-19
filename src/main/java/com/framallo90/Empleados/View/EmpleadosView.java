/**
 * Clase que proporciona métodos para la interacción con el usuario para la gestión de empleados.
 *
 * @author Framballo90
 * @since v1.0
 */
package com.framallo90.Empleados.View;

import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.consola.Consola;

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
        username = Consola.ingresarXStringSimple("username");
        while (true){
            password = Consola.ingresarXStringSimple("password");
            if (validarPassword(password)) break;
            else if (password.equals("0")) return null;
            else Consola.soutString("Contraseña inválida. Debe tener al menos:" +
                        "\n 1 letra minúscula" +
                        "\n 1 letra mayúscula" +
                        "\n 1 número" +
                        "\n 1 caracter especial (!#$%&);");
        }
        tipo = this.generarTipo();

        return new Empleados(nombre, apellido, dni, 0, username, password, tipo);
    }

    private boolean validarPassword(String password) {

        // Patrón de expresión regular para la validación
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).*$";

        // Validación usando expresiones regulares
        return password.matches(regex);
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
    public void printMenuAdministrador(){
        System.out.println("1. Agregar empleado.");
        System.out.println("2. Modificar empleado.");
        System.out.println("3. Elimniar empleado.");
        System.out.println("4. Buscar un empleado");
        System.out.println("5. Historial de empleado.");
        System.out.println("6. SALIR.");
    }

    public void mostrarEmpleado(Empleados empleados){

        System.out.println("=========================================");
        System.out.println("ID: " + empleados.getId());
        System.out.println("Nombre: " + empleados.getApellido() + ", " + empleados.getNombre());
        System.out.println("Dni: "+empleados.getDni());
        System.out.println("Tipo de usuario: "+empleados.getTipo());
        System.out.println("Autos vendidos: "+empleados.getAutosvendidos());
        System.out.println("username: "+empleados.getUsername());
        System.out.println("=========================================");
    }
    public void muestroEmpleados(List<Empleados> empleados){
        for (Empleados empleados1 : empleados){
            mostrarEmpleado(empleados1);
        }
    }
}