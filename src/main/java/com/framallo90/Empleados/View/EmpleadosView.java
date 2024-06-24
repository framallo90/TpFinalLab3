/**
 * Clase que proporciona métodos para la interacción con el usuario para la gestión de empleados.
 * Permite generar, mostrar y manejar objetos Empleados a través de la consola.
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
        while (!validarDNI(dni))
        {
            Consola.soutAlertString("El dni NO es válido, reintentar.");
            dni = Consola.ingresarXInteger("dni");
        }
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
        Empleados devol = new Empleados(nombre, apellido, dni, 0, username, password, tipo);
        System.out.println("Empleado creado:");
        this.mostrarEmpleado(devol);
        return devol;
    }

    /**
     * Valida que la contraseña cumpla con los requisitos mínimos.
     *
     * @param password Contraseña a validar.
     * @return true si la contraseña es válida, false en caso contrario.
     */
    private boolean validarPassword(String password) {
        // Patrón de expresión regular para la validación
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).*$";

        // Validación usando expresiones regulares
        return password.matches(regex);
    }
    /**
     * Valida un DNI argentino representado como un número entero.
     *
     * @param dni El número de DNI a validar.
     * @return true si el DNI es válido, false si no lo es.
     */
    public static boolean validarDNI(Integer dni) {
        // Convertir el DNI a String y validar
        String dniStr = String.valueOf(dni);
        return validarDNI(dniStr);
    }


    /**
     * Valida un DNI argentino representado como una cadena de caracteres.
     *
     * @param dniStr El número de DNI como cadena a validar.
     * @return true si el DNI es válido, false si no lo es.
     */
    public static boolean validarDNI(String dniStr) {
        // Verificar longitud y formato numérico
        if (!dniStr.matches("[0-9]{7,8}")) {
            return false;
        }
        return (dniStr.length()==8);
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
                System.out.println("Ingresar un dato válido");
            }
        }
    }

    /**
     * Imprime el menú de opciones disponible para un administrador de empleados.
     */
    public void printMenuAdministrador() {
        System.out.println("--- MENU EMPLEADOS (administrador) ---");
        System.out.println("1. Agregar empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Eliminar empleado");
        System.out.println("4. Buscar un empleado");
        System.out.println("5. Historial de empleado.");
        System.out.println("0. Volver");
    }

    public void printMenuModifEmpleado(){
        Consola.soutString("""
                    --- MODIFICACIÓN EMPLEADO ---
                    1. Nombre
                    2. Apellido
                    3. Cantidad de autos vendidos
                    4. Username
                    5. Password
                    6. Tipo del empleado
                    0. Volver
                    """);
    }

    /**
     * Muestra por consola la información detallada de un empleado.
     *
     * @param empleados El objeto Empleados que se desea mostrar.
     */
    public void mostrarEmpleado(Empleados empleados) {
        System.out.println("=========================================");
        System.out.println("ID: " + empleados.getId());
        System.out.println("Nombre: " + empleados.getApellido() + ", " + empleados.getNombre());
        System.out.println("DNI: " + empleados.getDni());
        System.out.println("Tipo de usuario: " + empleados.getTipo());
        System.out.println("Autos vendidos: " + empleados.getAutosvendidos());
        System.out.println("Username: " + empleados.getUsername());
        System.out.println("=========================================");
    }

    /**
     * Muestra por consola la información detallada de varios empleados.
     *
     * @param empleados La lista de objetos Empleados que se desea mostrar.
     */
    public void muestroEmpleados(List<Empleados> empleados) {
        for (Empleados empleado : empleados) {
            mostrarEmpleado(empleado);
        }
    }
}
