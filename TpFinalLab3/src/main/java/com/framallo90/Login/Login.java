package com.framallo90.Login;

import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Empleados.Model.Repository.EmpleadosRepository;
import com.framallo90.consola.Consola;

import java.util.List;

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
    private EmpleadosRepository empleadosRepository;

    /**
     * Constructor que inicializa el repositorio de empleados utilizado para la autenticación.
     */
    public Login() {
        empleadosRepository = new EmpleadosRepository();
    }

    /**
     * Realiza el proceso de inicio de sesión para los empleados.
     *
     * @return El objeto Empleados correspondiente al usuario autenticado, o null si las credenciales son incorrectas.
     */
    public Empleados login() {
        //cada vez que se llame a iniciar secion se va a actualizar con los cambios en usuario como nuevos usuarios, usuarios que ya no tienen acceso al sistema o ascendidos/degrdados

        String username = Consola.ingresarXStringSimple("Nombre del Usuario");
        String password = Consola.ingresarXStringSimple("la contraseña");

        // Carga la lista de empleados desde el repositorio.
        List<Empleados> empleados = empleadosRepository.getList();

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
