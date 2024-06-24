package com.framallo90.Venta.Controller;
import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.MetodoDePago.Controller.MetodoController;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.Venta.Model.Entity.Venta;
import com.framallo90.Venta.Model.Repository.VentaRepository;
import com.framallo90.Venta.View.VentaView;
import com.framallo90.consola.Consola;
import java.time.LocalDate;
/**
 * Controlador que gestiona las operaciones relacionadas con las ventas de automóviles.
 * Permite agregar, mostrar, actualizar y eliminar ventas, además de proporcionar un menú
 * interactivo para manejar estas operaciones.
 *
 * Ejemplo de uso:
 * ```
 * VentaController ventaController = new VentaController(...);
 * ventaController.menuVentas();
 * ```
 *
 * @author Framallo
 * @version 1.0
 */
public class VentaController {
    private final EmpleadosController empleadosController;
    private final CompradorController compradorController;
    private final AutomovilController automovilController;
    private final MetodoController metodoController;
    private final VentaView ventaView;
    private final VentaRepository ventaRepository;
    /**
     * Constructor que inicializa el controlador de ventas con los controladores y repositorios necesarios.
     *
     * @param empleadosController Controlador de empleados.
     * @param compradorController Controlador de compradores.
     * @param automovilController Controlador de automóviles.
     * @param metodoController    Controlador de métodos de pago.
     * @param ventaView           Vista de ventas.
     * @param ventaRepository     Repositorio de ventas.
     */
    public VentaController(EmpleadosController empleadosController, CompradorController compradorController,
                           AutomovilController automovilController, MetodoController metodoController,
                           VentaView ventaView, VentaRepository ventaRepository) {
        this.empleadosController = empleadosController;
        this.compradorController = compradorController;
        this.automovilController = automovilController;
        this.metodoController = metodoController;
        this.ventaView = ventaView;
        this.ventaRepository = ventaRepository;
    }
    /**
     * Agrega una nueva venta al sistema. Permite seleccionar un empleado vendedor, un comprador,
     * un automóvil en stock, generar el método de pago y registrar la venta en el repositorio.
     *
     * @throws InvalidIdNotFound Si no se encuentra el empleado, comprador o automóvil correspondiente.
     */
    public void add() throws InvalidIdNotFound {
        // Selección del empleado vendedor
        empleadosController.mostrarHistorial();
        Empleados empleados = this.empleadosController.find(Consola.ingresarXInteger("id del empleado vendedor"));
        if (empleados == null) {
            throw new InvalidIdNotFound("empleado no encontrado");
        }
        // Selección del comprador
        this.compradorController.verHisorial();
        Comprador comprador = this.compradorController.find(Consola.ingresarXInteger("id del comprador actual"));
        if (comprador == null) {
            throw new InvalidIdNotFound("comprador no encontrado");
        }

        // Selección del automóvil en stock
        automovilController.mostrarAutomovilesEnStock();
        Integer id = Consola.ingresarXInteger("id del automovil en stock");
        Automovil automovil = this.automovilController.find(id);
        if (automovil == null) {
            throw new InvalidIdNotFound("automovil no encontrado");
        }

        // Generación del método de pago
        LocalDate fecha = LocalDate.now();
        MetodoDePago metodoDePago = this.metodoController.cargarMDP(automovil.getPrecio());
        Venta venta = this.ventaView.generarVenta(empleados,comprador,automovil,fecha,metodoDePago);
        this.ventaRepository.add(venta);
        this.automovilController.borrarAutomovilEnStockPorId(id);

        // Generación y registro de la venta
        venta = this.ventaView.generarVenta(empleados, comprador, automovil, fecha, metodoDePago);
        this.ventaRepository.add(venta);
    }
    /**
     * Muestra los detalles de una venta específica seleccionada por su ID.
     */
    public void show() {
        Venta buscar = this.ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
        if (buscar != null)
            this.ventaView.mostrarVenta(buscar);
        else Consola.soutAlertString("No existe una venta con el id ingresado.");
    }
    /**
     * Actualiza una venta existente seleccionada por su ID, permitiendo modificar el empleado vendedor,
     * el comprador, el automóvil o el método de pago asociado.
     *
     * @throws InvalidIdNotFound Si no se encuentra la venta correspondiente.
     */
    public void update() throws InvalidIdNotFound {
        Venta buscar = this.ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
        if (buscar == null) {
            throw new InvalidIdNotFound("No se ha encontrado una venta.");
        }
        modifVenta(buscar);
    }

    /**
     * Elimina una venta existente seleccionada por su ID.
     *
     * @throws InvalidIdNotFound Si no se encuentra la venta correspondiente.
     */
    public void remover() throws InvalidIdNotFound {
        Venta buscar = this.ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
        if (buscar == null) {
            throw new InvalidIdNotFound("No se ha encontrado una venta.");
        }
        try {
            ventaRepository.restoVenta(buscar);
            this.ventaRepository.remove(buscar.getIdVenta());
        } catch (Exception e) {
            Consola.soutAlertString(e.getMessage());
        }
    }
    /**
     * Permite modificar diferentes aspectos de una venta específica, como el empleado vendedor,
     * el comprador, el automóvil o el método de pago asociado.
     *
     * @param venta La venta a modificar.
     */
    public void modifVenta(Venta venta) {
        while (true) {
            this.ventaView.printMenuModifVenta();
            switch (Consola.ingresarXInteger("eleccion")) {
                case 1: // Modificar empleado
                    empleadosController.modificacion(venta.getEmpleados());
                    break;
                case 2: // Modificar comprador
                    compradorController.update(venta.getComprador());
                    break;
                case 3: //automovil
                    /*
                    try {
                        venta.setAutomovil(automovilController.cambiarCoche(venta.getAutomovil()));
                    } catch (InvalidIdNotFound e) {
                        Consola.soutString(e.getMessage());
                    }
                    break;
                    */
                case 4: //mtodo de pago
                    metodoController.updateMDP(venta.getTransaccion(), venta.getAutomovil().getPrecio());
                    break;
                case 0: // Salir
                    return;
                default:
                    Consola.soutAlertString("Opción inválida, reintentar.");
                    break;
            }
        }
    }

    /**
     * Muestra un menú interactivo para manejar las operaciones relacionadas con las ventas.
     * Permite agregar, mostrar, actualizar y eliminar ventas, además de visualizar el historial completo.
     */
    public void menuVentas() {
        int eleccion;
        do {
            this.ventaView.printMenuVentas();
            eleccion = Consola.ingresarXInteger("eleccion");
            switch (eleccion) {
                case 0: // Salir
                    Consola.soutString("saliendo...");
                    break;
                case 1: // Agregar venta
                    try {
                        this.add();
                    } catch (InvalidIdNotFound e) {
                        Consola.soutAlertString(e.getMessage());
                    }
                    break;
                case 2: // Mostrar venta
                    this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                    if (!this.ventaRepository.isEmpty())
                        this.show();
                    break;
                case 3: // Modificar venta
                    try {
                        this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                        if (!this.ventaRepository.isEmpty())
                            this.update();
                        break;
                    } catch (InvalidIdNotFound e) {
                        Consola.soutAlertString(e.getMessage());
                    }
                    break;
                case 4: // Eliminar venta
                    try {
                        this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                        if (!this.ventaRepository.isEmpty())
                            this.remover();
                        break;
                    } catch (InvalidIdNotFound e) {
                        Consola.soutAlertString(e.getMessage());
                    }
                    break;
                case 5: // Mostrar todas las ventas
                    this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                    break;
                default:
                    Consola.soutAlertString("Ingresar un dato válido.");
                    break;
            }
        } while (eleccion != 0);
    }
}
