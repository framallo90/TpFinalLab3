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
import com.framallo90.Excepciones.CeroAdminsException;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            if (this.compruebaDni(nuevoEmpleado.getDni())) {
                // El empleado ya existe. Se disminuye el contador de empleados en 1.
                Empleados.setCont(Empleados.getCont() - 1);
            } else {
                // El empleado no existe. Se agrega al repositorio.
                empleadosRepository.add(nuevoEmpleado);
            }
        }
    }

    /**
     * Comprueba si el dni recibido existe entre los empleados existentes.
     *
     * @param dni El dni a verificar.
     * @return true si se encuentra un empleado con el mismo dni o false en caso de no haberlo encontrado.
     */
    private boolean compruebaDni(Integer dni){
        List<Empleados> list = new ArrayList<>(this.empleadosRepository.getList());

        for (Empleados empleados : list){
            if(empleados.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }

    /**
     * Permite al usuario modificar un empleado existente.
     */
    public void modificarEmpleado() {
        Integer idEmpleado = Consola.ingresarXInteger("el ID del Empleado");
        try{
            Empleados empleadoAModificar = empleadosRepository.find(idEmpleado);

            if (empleadoAModificar != null) {
                // El empleado se encuentra. Se procede a la modificación.
                modificacion(empleadoAModificar);
                empleadosRepository.update(idEmpleado,empleadoAModificar);
            }
        }catch (InvalidIdNotFound ex){
            Consola.soutAlertString(ex.getMessage());
        }
    }

    /**
     * Permite al usuario modificar un empleado existente.
     *
     * @param empleadoAModificar El empleado a modificar.
     */


    /**
     * Permite al usuario modificar los datos de un empleado existente.
     *
     * @param empleado El empleado a modificar.
     */
    public void modificacion(Empleados empleado) {
        while (true) {
            this.empleadosView.printMenuModifEmpleado();
            String opcion = String.valueOf(Consola.ingresarXInteger("un campo para modificar Empleado"));
            switch (opcion) {
                case "0":
                    // Salir del menú de modificación.
                    return;
                case "1":
                    // Modificar el nombre del empleado.
                    empleadosRepository.cambioNombre(empleado, Consola.ingresarXString("el Nuevo Nombre"));
                    break;
                case "2":
                    // Modificar el apellido del empleado.
                    empleadosRepository.cambioApellido(empleado, Consola.ingresarXString("el Nuevo Apellido"));
                    break;
                case "3":
                    // Modificar la cantidad de autos vendidos del empleado.
                    empleadosRepository.cambioAutosVendidos(empleado, Consola.ingresarXInteger("la nueva Cantidad de Autos vendidos"));
                    Consola.limpiarBuffer();
                    break;
                case "4":
                    // Modificar el username del empleado.
                    empleadosRepository.cambioUsername(empleado, Consola.ingresarXStringSimple("el Nuevo Username"));
                    break;
                case "5":
                    // Modificar la contraseña del empleado.
                    empleadosRepository.cambioPassword(empleado, Consola.ingresarXStringSimple("la Nueva Password"));
                    break;
                case "6":
                    // Modificar el tipo de empleado.
                    empleadosRepository.cambioTipo(empleado, Consola.ingresarXString("el Nuevo Tipo"));
                    break;
                default:
                    // Dato ingresado no válido.
                    Consola.soutAlertString("Opción Inválida. Reintentar!.");
                    break;
            }
        }
    }

    /**
     * Permite al usuario eliminar un empleado existente.
     */
    public void removeEmpleado()  {
        Integer id = Consola.ingresarXInteger("el ID del Empleado");
        try {
            empleadosRepository.remove(id);
        } catch (CeroAdminsException e) {
            Consola.soutAlertString(e.getMessage());
        }
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
        Empleados buscar = this.empleadosRepository.find(Consola.ingresarXInteger("el ID del Empleado"));
        if (buscar == null) {
            Consola.soutAlertString("NO se ha encontrado el Empleado.");
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
            opt = Consola.ingresarXInteger("una opcion del Menu Administrador");
            switch (opt) {
                case 0:
                    System.out.println("Saliste del Menu Administrador.");
                    break;
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    mostrarHistorial();
                    modificarEmpleado();
                    break;
                case 3:
                    this.removeEmpleado();
                case 4:
                    mostrarHistorial();
                    mostrar();
                    break;
                case 5:
                    mostrarHistorial();
                    break;
                default:
                    System.out.println("Opción Inválida. Reintentar!.");
                    break;
            }
        } while (opt != 0);
    }

}