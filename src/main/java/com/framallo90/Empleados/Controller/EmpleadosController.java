/**
 * Controlador principal de la aplicación para la gestión de empleados.
 *
 * Este controlador se encarga de la interacción entre la vista (EmpleadosView) y el repositorio de datos (EmpleadosRepository) para realizar las operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los empleados del sistema.
 *
 * @author Framballo90
 * @since v1.0
 */
package com.framallo90.Empleados.Controller;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Empleados.Model.Repository.EmpleadosRepository;
import com.framallo90.Empleados.View.EmpleadosView;
import com.framallo90.consola.Consola;
public class EmpleadosController {
    /**
     * Repositorio de datos para la gestión de empleados.
     */
    private EmpleadosRepository empleadosRepository;

    /**
     * Vista para la interacción con el usuario.
     */
    private EmpleadosView empleadosView;

    /**
     * Constructor del controlador.
     *
     * @param empleadosRepository Repositorio de datos para la gestión de empleados.
     * @param empleadosView Vista para la interacción con el usuario.
     */
    public EmpleadosController(EmpleadosRepository empleadosRepository, EmpleadosView empleadosView) {
        this.empleadosRepository = empleadosRepository;
        this.empleadosView = empleadosView;
    }

    /**
     * Permite al usuario crear un nuevo empleado.
     */
    public void crearEmpleado() {
        Empleados nuevoEmpleado = empleadosView.generarEmpleado();

        if (empleadosRepository.find(String.valueOf(nuevoEmpleado.getDni())) != null) {
            // El empleado ya existe. Se disminuye el contador de empleados en 1.
            Empleados.setCont(Empleados.getCont() - 1);
        } else {
            // El empleado no existe. Se agrega al repositorio.
            empleadosRepository.add(nuevoEmpleado);
        }
    }

    /**
     * Permite al usuario modificar un empleado existente.
     */
    public void modificarEmpleado() {
        String dniEmpleado = String.valueOf(Consola.ingresarXInteger("dni del empleado"));
        Empleados empleadoAModificar = empleadosRepository.find(dniEmpleado);

        if (empleadoAModificar != null) {
            // El empleado se encuentra. Se procede a la modificación.
            modificacion(empleadoAModificar);
        } else {
            // El empleado no se encuentra. Se informa al usuario.
            Consola.soutString("No se ha encontrado al empleado con DNI: " + dniEmpleado);
        }
    }/**
     * Permite al usuario modificar los datos de un empleado existente.
     *
     * @param empleado El empleado a modificar.
     */
    private void modificacion(Empleados empleado) {
        while (true) {
            Consola.soutString("""
                         opción para modifación:
                         1) nombre
                         2) apellido
                         3) cantidad de autos vendidos
                         4) username
                         5) password
                         6) tipo de empleado
                         0) salir
                         """);

            String opcion = String.valueOf(Consola.ingresarXInteger("opcion"));
            Consola.limpiarBuffer();

            switch (opcion) {
                case "0":
                    // Salir del menú de modificación.
                    return;
                case "1":
                    // Modificar el nombre del empleado.
                    empleadosRepository.cambioNombre(empleado, Consola.ingresarXString("nuevo nombre"));
                    break;
                case "2":
                    // Modificar el apellido del empleado.
                    empleadosRepository.cambioApellido(empleado, Consola.ingresarXString("nuevo apellido"));
                    break;
                case "3":
                    // Modificar la cantidad de autos vendidos del empleado.
                    empleadosRepository.cambioAutosVendidos(empleado, Consola.ingresarXInteger("cantidad de autos vendidos"));
                    Consola.limpiarBuffer();
                    break;
                case "4":
                    // Modificar el username del empleado.
                    empleadosRepository.cambioUsername(empleado, Consola.ingresarXString("nuevo username"));
                    break;
                case "5":
                    // Modificar la contraseña del empleado.
                    //Tener en cuenta que en este momento la contraseña no puede tener numeros por cómo funciona 'Consola.ingresarXString()'
                    empleadosRepository.cambioPassword(empleado, Consola.ingresarXString("nueva password"));
                    break;
                case "6":
                    // Modificar el tipo de empleado.
                    empleadosRepository.cambioTipo(empleado, Consola.ingresarXString("nuevo tipo"));
                    break;
                default:
                    // Dato ingresado no válido.
                    Consola.soutString("Ingrese un dato válido.");
                    break;
            }
        }
    }

    /**
     * Permite al usuario eliminar un empleado existente.
     */
    public void removeEmpleado() {
        String dniEmpleado = Consola.ingresarXString("dni del empleado");
        empleadosRepository.remove(dniEmpleado);
    }

    //documentar
    public Empleados find(){
        Empleados buscar = this.empleadosRepository.find(Consola.ingresarXString("dni del empleado"));
        if (buscar == null )Consola.soutString("No se ha encontrado el empleado.");
        return buscar;
    }

    public void mostrar(){
        Empleados buscar = this.empleadosRepository.find(Consola.ingresarXString("dni del empleado"));
        if (buscar == null )Consola.soutString("No se ha encontrado el empleado.");
        else {this.empleadosView.muestroEmpleado(buscar);}
    }

    public void mostrarHistorial(){
        this.empleadosView.muestroEmpleados(this.empleadosRepository.getList());
    }

    public void menuControllerEmpleados()
    {
        int opt;
        do {
            System.out.println("1. Agregar empleado.");
            System.out.println("2. Modificar empleado.");
            System.out.println("3. Elimniar empleado.");
            System.out.println("4. Buscar un empleado");
            System.out.println("5. Historial de empleado.");
            System.out.println("6. SALIR.");
            opt = Consola.ingresarXInteger("opcion");
            switch (opt){
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    modificarEmpleado();
                    break;
                case 3:
                    removeEmpleado();
                    break;
                case 4:
                    mostrar();
                    break;
                case 5:
                    mostrarHistorial();
                    break;
                case 6:
                    System.out.println("Saliendo....");
                    break;
                default:
                    System.out.println("Opcion invalida vuelva a intentarlo");
                    break;
            }
        }while (opt!=6);
    }

}