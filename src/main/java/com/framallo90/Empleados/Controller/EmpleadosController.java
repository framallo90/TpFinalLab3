/**
 * Controlador principal de la aplicación para la gestión de empleados.
 *
 * Este controlador se encarga de la interacción entre la vista (EmpleadosView) y el repositorio de datos (EmpleadosRepository)
 * para realizar las operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los empleados del sistema.
 *
 * @autor Framballo90
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

        if (nuevoEmpleado != null) {
            if (empleadosRepository.find(nuevoEmpleado.getDni()) != null) {
                // El empleado ya existe. Se disminuye el contador de empleados en 1.
                Empleados.setCont(Empleados.getCont() - 1);
            } else {
                // El empleado no existe. Se agrega al repositorio.
                empleadosRepository.add(nuevoEmpleado);
            }
        } else {
            Consola.soutString("Error al crear un empleado. Volviendo...");
        }
    }

    /**
     * Permite al usuario modificar un empleado existente.
     */
    public void modificarEmpleado() {
        Integer idEmpleado = Consola.ingresarXInteger("ID del empleado");
        Empleados empleadoAModificar = empleadosRepository.find(idEmpleado);

        if (empleadoAModificar != null) {
            // El empleado se encuentra. Se procede a la modificación.
            modificacion(empleadoAModificar);
        } else {
            // El empleado no se encuentra. Se informa al usuario.
            Consola.soutString("No se ha encontrado al empleado de id: " + idEmpleado);
        }
    }

    /**
     * Permite al usuario modificar un empleado existente.
     *
     * @param empleadoAModificar El empleado a modificar.
     */
    public void modificarEmpleado(Empleados empleadoAModificar) {
        modificacion(empleadoAModificar);
    }

    /**
     * Permite al usuario modificar los datos de un empleado existente.
     *
     * @param empleado El empleado a modificar.
     */
    private void modificacion(Empleados empleado) {
        while (true) {
            this.empleadosView.printMenuModifEmpleado();
            String opcion = String.valueOf(Consola.ingresarXInteger("opcion"));
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
                    empleadosRepository.cambioUsername(empleado, Consola.ingresarXStringSimple("nuevo username"));
                    break;
                case "5":
                    // Modificar la contraseña del empleado.
                    empleadosRepository.cambioPassword(empleado, Consola.ingresarXStringSimple("nueva password"));
                    break;
                case "6":
                    // Modificar el tipo de empleado.
                    empleadosRepository.cambioTipo(empleado, Consola.ingresarXString("nuevo tipo"));
                    break;
                default:
                    // Dato ingresado no válido.
                    Consola.soutAlertString("Ingrese un dato válido.");
                    break;
            }
        }
    }

    /**
     * Permite al usuario eliminar un empleado existente.
     */
    public void removeEmpleado() {
        Integer id = Consola.ingresarXInteger("id del empleado");
        empleadosRepository.remove(id);
    }

    /**
     * Encuentra un empleado por su ID.
     *
     * @param id El ID del empleado a buscar.
     * @return El empleado encontrado, o null si no se encuentra.
     */
    public Empleados find(Integer id) {
        Empleados buscar = this.empleadosRepository.find(id);
        return buscar;
    }

    /**
     * Muestra los detalles de un empleado.
     */
    public void mostrar() {
        Empleados buscar = this.empleadosRepository.find(Consola.ingresarXInteger("id del empleado"));
        if (buscar == null) {
            Consola.soutString("No se ha encontrado el empleado.");
        } else {
            this.empleadosView.mostrarEmpleado(buscar);
        }
    }

    /**
     * Muestra el historial de todos los empleados.
     */
    public void mostrarHistorial() {
        this.empleadosView.muestroEmpleados(this.empleadosRepository.getList());
    }

    /**
     * Muestra el menú de control de empleados y gestiona las interacciones del usuario.
     */
    public void menuControllerEmpleados() {
        int opt;
        do {
            this.empleadosView.printMenuAdministrador();
            opt = Consola.ingresarXInteger("opcion");
            switch (opt) {
                case 0:
                    System.out.println("Saliendo....");
                    break;
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    mostrarHistorial();
                    modificarEmpleado();
                    break;
                case 3:
                    if (this.empleadosRepository.contAdmins() > 1) {
                        mostrarHistorial();
                        removeEmpleado();
                    }
                    else if (this.empleadosRepository.contAdmins()==1)
                        Consola.soutAlertString("Tiene que haber al menos 1 administrador en el sisetma.");
                    break;
                case 4:
                    mostrarHistorial();
                    mostrar();
                    break;
                case 5:
                    mostrarHistorial();
                    break;
                default:
                    System.out.println("Opcion invalida vuelva a intentarlo");
                    break;
            }
        } while (opt != 0);
    }
}