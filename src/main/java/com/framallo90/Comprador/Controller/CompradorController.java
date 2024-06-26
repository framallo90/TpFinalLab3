package com.framallo90.Comprador.Controller;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Comprador.Model.Repository.CompradorRepository;
import com.framallo90.Comprador.View.CompradorView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;

/**
 * Controlador para la gestión de operaciones relacionadas con los compradores en el sistema.
 */
public class CompradorController {
    private final CompradorView compradorView;
    private final CompradorRepository compradorRepository;

    /**
     * Constructor de la clase CompradorController.
     * @param compradorView Vista asociada a los compradores.
     * @param compradorRepository Repositorio de datos de los compradores.
     */
    public CompradorController(CompradorView compradorView, CompradorRepository compradorRepository) {
        this.compradorView = compradorView;
        this.compradorRepository = compradorRepository;
    }

    /**
     * Método que maneja el menú de opciones para los compradores.
     * Permite agregar, modificar, eliminar, buscar y mostrar el historial de compradores.
     */
    public void compradorMenu() {
        int opt;
        do {
            System.out.println("--- MENU CLIENTES ---");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Modificar Cliente");
            System.out.println("3. Eliminar Cliente");
            System.out.println("4. Buscar un Cliente");
            System.out.println("5. Historial de Clientes");

            System.out.println("0. Volver.");
            System.out.println("----------------------");
            opt = Consola.ingresarXInteger("una opcion del Menu Cliente");

            switch (opt) {
                case 1:
                    add();
                    break;
                case 2:
                    verHisorial();
                    Comprador comprador;
                    try {
                        comprador = compradorRepository.find(Consola.ingresarXInteger("el ID del Comprador"));
                        System.out.println("1. Nombre");
                        System.out.println("2. Apellido");
                        System.out.println("3. DNI");
                        System.out.println("4. E-Mail");

                        System.out.println("0. Volver");
                        opt = Consola.ingresarXInteger("un campo para modificar de Comprado");

                        switch (opt) {
                            case 1:
                                comprador.setNombre(Consola.ingresarXString("el Nuevo Nombre"));
                                this.compradorRepository.update(comprador.getId(),comprador);
                                break;
                            case 2:
                                comprador.setApellido(Consola.ingresarXString("el Nuevo Apellido"));
                                this.compradorRepository.update(comprador.getId(),comprador);
                                break;
                            case 3:
                                comprador.setDni(Consola.ingresarXInteger("el Nuevo DNI"));
                                this.compradorRepository.update(comprador.getId(),comprador);
                                break;
                            case 4:
                                comprador.setEmail(compradorView.ingresoEmail());
                                this.compradorRepository.update(comprador.getId(),comprador);
                                break;
                            case 0:
                                break;
                            default:
                                Consola.soutAlertString("Opción Inválida. Reintentar!.");
                                break;
                        }
                        this.compradorRepository.update(comprador.getId(),comprador);
                    } catch (InvalidIdNotFound e) {
                        Consola.soutAlertString(e.getMessage());
                    }

                    break;
                case 3:
                    verHisorial();
                    remove();
                    break;
                case 4:
                    verHisorial();
                    show();
                    break;
                case 5:
                    verHisorial();
                    break;
                case 0:
                    System.out.println("Saliste del Menu Cliente.");
                    break;
                default:
                    Consola.soutAlertString("Opción Inválida. Reintentar!.");
                    break;
            }

        } while (opt != 0);
    }

    /**
     * Método para agregar un nuevo comprador al sistema.
     * Se solicita al usuario ingresar nombre, apellido, DNI y email del comprador.
     */
    public void add() {
        String nombre = Consola.ingresarXString("el Nombre");
        String apellido = Consola.ingresarXString("el Apellido");
        Integer dni = Consola.ingresarXInteger("el DNI");
        String email = compradorView.ingresoEmail();
        Comprador comprador = new Comprador(nombre, apellido, dni, email);
        this.compradorRepository.add(comprador);
    }

    /**
     * Método para eliminar un comprador del sistema.
     * Se solicita al usuario ingresar el ID del comprador a eliminar.
     */
    public void remove() {
        try{
            compradorRepository.remove(Consola.ingresarXInteger("el ID del Comprador"));
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }



    /**
     * Sobrecarga del método update que permite actualizar la información de un comprador directamente.
     * @param comprador El comprador cuya información se desea actualizar.
     */


    /**
     * Método para mostrar la información de un comprador específico.
     * Se solicita al usuario ingresar el ID del comprador buscado y se muestra su información si existe.
     */
    public void show() {
        Integer id = Consola.ingresarXInteger("el ID del Comprador buscado");
        try{
            Comprador comprador = compradorRepository.find(id);
            compradorView.muestroUnComprador(comprador);
        }catch (InvalidIdNotFound e){
            Consola.soutAlertString(e.getMessage());
        }
    }

    /**
     * Método para obtener un comprador mediante su ID.
     * @param id ID del comprador buscado.
     * @return El objeto Comprador encontrado o null si no existe.
     */
    public Comprador find(Integer id) throws InvalidIdNotFound{
        this.compradorView.muestroCompradores(this.compradorRepository.getsetCompradores());
        Comprador devol = this.compradorRepository.find(Consola.ingresarXInteger("id del comprador"));

        return devol;
    }

    /**
     * Método para mostrar el historial completo de compradores almacenados en el sistema.
     * Utiliza la vista para mostrar la lista de compradores.
     */
    public void verHisorial() {
        compradorView.muestroCompradores(this.compradorRepository.getsetCompradores());
    }
}
