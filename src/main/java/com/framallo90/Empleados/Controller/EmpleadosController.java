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
        for (Empleados empleados : this.empleadosRepository.getList()){
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
            modificacion(empleadoAModificar);
        }catch (InvalidIdNotFound ex){
            Consola.soutAlertString(ex.getMessage());
        }catch (IllegalArgumentException ex){
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
    public void modificacion(Empleados empleado) throws InvalidIdNotFound {
        while (true) {
            this.empleadosView.printMenuModifEmpleado();
            Integer opcion = (Consola.ingresarXInteger("un campo para modificar Empleado"));
            switch (opcion) {
                case 0:
                    // Salir del menú de modificación.
                    return;
                case 1:

                    // Modificar el nombre del empleado.
                    empleado.setNombre(Consola.ingresarXString("el Nuevo Nombre"));
                    this.empleadosRepository.update(empleado.getId(),empleado);
                    break;
                case 2:
                    // Modificar el apellido del empleado.
                    empleado.setApellido(Consola.ingresarXString("el Nuevo Apellido"));
                    this.empleadosRepository.update(empleado.getId(),empleado);
                    break;
                case 3:
                    empleado.setAutosvendidos(Consola.ingresarXInteger("la nueva Cantidad de Autos vendidos"));
                    // Modificar la cantidad de autos vendidos del empleado.
                    this.empleadosRepository.update(empleado.getId(),empleado);
                    //Consola.limpiarBuffer();
                    break;
                case 4:
                    empleado.setUsername(Consola.ingresarXStringSimple("el Nuevo Username"));
                    // Modificar el username del empleado.
                    this.empleadosRepository.update(empleado.getId(),empleado);

                    break;
                case 5:
                    empleado.setPassword(Consola.ingresarXStringSimple("la Nueva Password"));
                    // Modificar la contraseña del empleado.
                    this.empleadosRepository.update(empleado.getId(),empleado);

                    break;
                case 6:
                    empleado.setTipo(Consola.ingresarXString("el Nuevo Tipo"));
                    // Modificar el tipo de empleado.
                    this.empleadosRepository.update(empleado.getId(),empleado);

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
    public void removeEmpleado(){

        Integer id = Consola.ingresarXInteger("el ID del Empleado");
        try {
            empleadosRepository.remove(id);
        } catch (CeroAdminsException e) {
            Consola.soutAlertString(e.getMessage());
        }catch (InvalidIdNotFound e){
            Consola.soutAlertString(e.getMessage());
        }
    }

    /**
     * Encuentra un empleado por su ID.
     *
     * @param id El ID del empleado a buscar.
     * @return El empleado encontrado, o null si no se encuentra.
     */
    public Empleados find(Integer id) throws InvalidIdNotFound{

        return this.empleadosRepository.find(id);

    }
    public void update(Integer id,Empleados empleados) throws InvalidIdNotFound {
        this.empleadosRepository.update(id,empleados);
    }

    /**
     * Muestra los detalles de un empleado.
     */
    public void mostrar() {
        Empleados buscar = null;
        try {
            buscar = this.empleadosRepository.find(Consola.ingresarXInteger("el ID del Empleado"));
            this.empleadosView.mostrarEmpleado(buscar);
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
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
                    mostrarHistorial();
                    removeEmpleado();
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