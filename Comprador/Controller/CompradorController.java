package com.framallo90.Comprador.Controller;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Comprador.Model.Repository.CompradorRepository;
import com.framallo90.Comprador.View.CompradorView;
import com.framallo90.consola.Consola;

/**
 * Controlador para la gestión de operaciones relacionadas con los compradores en el sistema.
 */
public class CompradorController {

    private CompradorView compradorView;
    private CompradorRepository compradorRepository;

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
            System.out.println("MENU CLIENTES");
            System.out.println("1. Agregar cliente.");
            System.out.println("2. Modificar cliente.");
            System.out.println("3. Eliminar cliente.");
            System.out.println("4. Buscar un cliente.");
            System.out.println("5. Historial de clientes.");
            System.out.println("0. Volver.");
            opt = Consola.ingresarXInteger("opcion");

            switch (opt) {
                case 1:
                    add();
                    break;
                case 2:
                    verHisorial();
                    update();
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
                    System.out.println("Saliendo....");
                    break;
                default:
                    System.out.println("Opción inválida, vuelva a intentarlo.");
                    break;
            }
        } while (opt != 0);
    }

    /**
     * Método para agregar un nuevo comprador al sistema.
     * Se solicita al usuario ingresar nombre, apellido, DNI y email del comprador.
     */
    public void add() {
        String nombre = Consola.ingresarXString("nombre");
        String apellido = Consola.ingresarXString("apellido");
        Integer dni = Consola.ingresarXInteger("dni");
        String email = compradorView.ingresoEmail();
        Comprador comprador = new Comprador(nombre, apellido, dni, email);
        this.compradorRepository.add(comprador);
    }

    /**
     * Método para eliminar un comprador del sistema.
     * Se solicita al usuario ingresar el ID del comprador a eliminar.
     */
    public void remove() {
        compradorRepository.remove(Consola.ingresarXInteger("id"));
    }

    /**
     * Método para actualizar la información de un comprador en el sistema.
     * Se solicita al usuario ingresar el ID del comprador y luego seleccionar qué atributo desea modificar.
     * Ofrece opciones para modificar nombre, apellido, DNI y email del comprador.
     */
    public void update() {
        int opt;
        Comprador comprador = compradorRepository.find(Consola.ingresarXInteger("id"));

        if (comprador != null) {
            do {
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. DNI");
                System.out.println("4. E-Mail");
                System.out.println("5. SALIR.");
                opt = Consola.ingresarXInteger("elemento a modificar");

                switch (opt) {
                    case 1:
                        compradorRepository.cambioNombre(comprador, Consola.ingresarXString("nuevo nombre"));
                        break;
                    case 2:
                        compradorRepository.cambioApellido(comprador, Consola.ingresarXString("nuevo apellido"));
                        break;
                    case 3:
                        compradorRepository.cambioDni(comprador, Consola.ingresarXInteger("nuevo DNI"));
                        break;
                    case 4:
                        compradorRepository.cambioEmail(comprador, compradorView.ingresoEmail());
                        break;
                    case 5:
                        System.out.println("Saliendo....");
                        break;
                    default:
                        System.out.println("Opción inválida, vuelva a intentarlo.");
                        break;
                }
            } while (opt != 5);
        }
    }

    /**
     * Sobrecarga del método update que permite actualizar la información de un comprador directamente.
     * @param comprador El comprador cuya información se desea actualizar.
     */
    public void update(Comprador comprador) {
        int opt;
        do {
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. DNI");
            System.out.println("4. E-Mail");
            System.out.println("5. SALIR.");
            opt = Consola.ingresarXInteger("elemento a modificar");

            switch (opt) {
                case 1:
                    compradorRepository.cambioNombre(comprador, Consola.ingresarXString("nuevo nombre"));
                    break;
                case 2:
                    compradorRepository.cambioApellido(comprador, Consola.ingresarXString("nuevo apellido"));
                    break;
                case 3:
                    compradorRepository.cambioDni(comprador, Consola.ingresarXInteger("nuevo DNI"));
                    break;
                case 4:
                    compradorRepository.cambioEmail(comprador, compradorView.ingresoEmail());
                    break;
                case 5:
                    System.out.println("Saliendo....");
                    break;
                default:
                    System.out.println("Opción inválida, vuelva a intentarlo.");
                    break;
            }
        } while (opt != 5);
    }

    /**
     * Método para mostrar la información de un comprador específico.
     * Se solicita al usuario ingresar el ID del comprador buscado y se muestra su información si existe.
     */
    public void show() {
        Integer id = Consola.ingresarXInteger("id del comprador buscado");
        Comprador comprador = compradorRepository.find(id);
        if (comprador != null)
            compradorView.muestroUnComprador(comprador);
    }

    /**
     * Método para obtener un comprador mediante su ID.
     * @param id ID del comprador buscado.
     * @return El objeto Comprador encontrado o null si no existe.
     */
    public Comprador find(Integer id) {
        return compradorRepository.find(id);
    }

    /**
     * Método para mostrar el historial completo de compradores almacenados en el sistema.
     * Utiliza la vista para mostrar la lista de compradores.
     */
    public void verHisorial() {
        compradorView.muestroCompradores(compradorRepository.getListaCompradores());
    }
}
