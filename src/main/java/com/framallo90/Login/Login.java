package com.framallo90.Login;

import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Empleados.Model.Repository.EmpleadosRepository;
import com.framallo90.consola.Consola;

import java.util.List;
import java.util.Set;

/**
 * Clase que gestiona el proceso de inicio de sesión para los empleados.
 * Permite autenticar a los empleados comparando sus credenciales (usuario y contraseña)
 * con los datos almacenados en un repositorio de empleados.
 *
 * Utiliza la clase Consola para interactuar con el usuario mediante la consola para ingresar
 * el nombre de usuario y la contraseña.
 *
 * Ejemplo de uso:
 * ```
 * Login login = new Login();
 * Empleados empleado = login.login();
 *
 * if (empleado != null) {
 *     System.out.println("Inicio de sesión exitoso para el usuario: " + empleado.getNombre());
 * } else {
 *     System.out.println("Credenciales inválidas.");
 * }
 * ```
 *
 * @author Framallo
 * @version 1.0
 */
public class Login {
    private static EmpleadosRepository empleadosRepository;

    /**
     * Constructor que inicializa el repositorio de empleados utilizado para la autenticación.
     */
    public Login() {
        empleadosRepository = new EmpleadosRepository();
    }

    public static void setEmpleadosRepository(EmpleadosRepository empleadosRepository) {
        Login.empleadosRepository = empleadosRepository;
    }

    /**
     * Realiza el proceso de inicio de sesión para los empleados.
     *
     * @return El objeto Empleados correspondiente al usuario autenticado, o null si las credenciales son incorrectas.
     */
    public Empleados login() {
        String username = Consola.ingresarXStringSimple("nombre de usuario");
        String password = Consola.ingresarXStringSimple("contraseña");

        // Carga la lista de empleados desde el repositorio.
        Set<Empleados> empleados = empleadosRepository.getEmpleados();

        if (empleados == null || empleados.isEmpty()) {
            System.err.println("La lista está vacía o inexistente.");
            return null;
        }

        // Recorre la lista de empleados y valida las credenciales.
        for (Empleados empleado : empleados) {
            if (empleado.getUsername().equals(username) && empleado.getPassword().equals(password)) {
                return empleado;
            }
        }

        // Si no encuentra coincidencias, retorna null.
        return null;
    }
}
