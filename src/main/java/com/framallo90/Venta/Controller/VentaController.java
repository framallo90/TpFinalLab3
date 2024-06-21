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

import java.time.DateTimeException;
import java.time.LocalDate;
public class VentaController {
    private EmpleadosController empleadosController;
    private CompradorController compradorController;
    private AutomovilController automovilController;
    private MetodoController metodoController;
    private VentaView ventaView;
    private VentaRepository ventaRepository;
    public VentaController(EmpleadosController empleadosController, CompradorController compradorController, AutomovilController automovilController, MetodoController metodoController, VentaView ventaView, VentaRepository ventaRepository) {
        this.empleadosController = empleadosController;
        this.compradorController = compradorController;
        this.automovilController = automovilController;
        this.metodoController = metodoController;
        this.ventaView = ventaView;
        this.ventaRepository = ventaRepository;
    }

    //documentar
    public void add() throws InvalidIdNotFound {
        empleadosController.mostrarHistorial(); /// para facilitar la eleccion
        Empleados empleados = this.empleadosController.find(Consola.ingresarXInteger("id del empleado vendedor"));
        if (empleados == null) {
            throw new InvalidIdNotFound("empleado no encontrado");
        }
        compradorController.verHisorial();
        Comprador comprador = this.compradorController.find(Consola.ingresarXInteger("id del comprador actual"));
        if (comprador == null) throw new InvalidIdNotFound("comprador no encontrado");

        automovilController.mostrarAutomovilesEnStock();
        Integer id = Consola.ingresarXInteger("id del automovil en stock");
        Automovil automovil = this.automovilController.find(id);

        if (automovil == null)throw new InvalidIdNotFound("automovil no encontrado");
        LocalDate fecha = LocalDate.now();
        MetodoDePago metodoDePago = this.metodoController.cargarMDP(automovil.getPrecio());
        Venta venta = this.ventaView.generarVenta(empleados,comprador,automovil,fecha,metodoDePago);
        this.ventaRepository.add(venta);
        this.automovilController.borrarAutomovilEnStockPorId(id);

    }
    public void show() throws InvalidIdNotFound {
        Venta buscar = this.ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
        if (buscar != null ) this.ventaView.mostrarVenta(buscar);
        else throw new InvalidIdNotFound("No se ha encontrado una venta");
    }
    public void update() throws InvalidIdNotFound{
        Venta buscar = this.ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
        if (buscar == null )throw new InvalidIdNotFound("No se ha encontrado una venta.");
        modifVenta(buscar);
    }
    public void remover() throws InvalidIdNotFound{
        Venta buscar = this.ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
        if (buscar == null )throw new InvalidIdNotFound("No se ha encontrado una venta.");
        try {
            this.ventaRepository.remove(buscar.getIdVenta());
        } catch (Exception e) {
            Consola.soutString(e.getMessage());
        }
    }

    public void modifVenta(Venta venta){
        while (true){
            this.ventaView.printMenuModifVenta();
            switch (Consola.ingresarXInteger("eleccion")){
                case 1: //empleado
                    empleadosController.modificarEmpleado(venta.getEmpleados());
                    break;
                case 2: //comprador
                    compradorController.update(venta.getComprador());
                    break;
                case 3: //automovil
                    venta.setAutomovil(automovilController.cambiarCoche(venta.getAutomovil()));
                    break;
                case 4: //fecha
                    updateFecha(venta.getFecha());
                    break;
                case 5: //mtodo de pago
                    metodoController.updateMDP(venta.getTransaccion(), venta.getAutomovil().getPrecio());
                    break;
                case 0: //salir
                    return;
                default:
                    Consola.soutString("saliendo");
                    break;
            }
        }
    }

    public void updateFecha(LocalDate fecha){
        while (true) {
            this.ventaView.printMenuModifFecha();
            Integer choice = Consola.ingresarXInteger("eleccion");
            switch (choice) {
                case 1:
                    try{
                        fecha = cambiarDia(fecha);
                    }catch(DateTimeException e){
                        Consola.soutString(e.getMessage());
                    }
                    break;
                case 2:
                    try{
                        fecha = cambiarMes(fecha);
                    }catch(DateTimeException e){
                        Consola.soutString(e.getMessage());
                    }
                    break;
                case 3:
                    try{
                        fecha = cambiarAnio(fecha);
                    }catch(DateTimeException e){
                        Consola.soutString(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del menú de modificación.");
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
    private  LocalDate cambiarDia(LocalDate localDate) {
        int day = Consola.ingresarXInteger("nuevo día");
        try {
            return localDate.withDayOfMonth(day);
        } catch (IllegalArgumentException e) {
            System.out.println("Día inválido para el mes actual. Intente nuevamente.");
            return localDate;
        } catch (DateTimeException e ){
            throw new DateTimeException(day + " no es un día válido.");
        }
    }

    private LocalDate cambiarMes(LocalDate localDate) {
        Integer month = Consola.ingresarXInteger("nuevo mes");
        try {
            return localDate.withMonth(month);
        } catch (IllegalArgumentException e) {
            System.out.println("Mes inválido. Intente nuevamente.");
            return localDate;
        } catch (DateTimeException e){
            throw new DateTimeException(month + " no es un mes válido.");
        }
    }

    private static LocalDate cambiarAnio(LocalDate localDate) {
        Integer year = Consola.ingresarXInteger("nuevo año");
        if (year < 1950||year > LocalDate.now().getYear())
            throw new DateTimeException(year + " no es válido. Ingresar un número entre 1950 y " + LocalDate.now().getYear()+".");
        return localDate.withYear(year);
    }

    public void menuVentas(){
        int eleccion;
        do{
            this.ventaView.printMenuVentas();
            eleccion = Consola.ingresarXInteger("eleccion");
            switch(eleccion){
                case 0: //salir
                    Consola.soutString("saliendo...");
                    break;
                case 1://agregar venta
                    try {
                        this.add();
                        break;
                    } catch (InvalidIdNotFound e) {
                        Consola.soutString(e.getMessage());
                    }
                    break;
                case 2://mostrar
                    while(true){
                        try {
                            this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                            this.show();
                            break;
                        } catch (InvalidIdNotFound e) {
                            Consola.soutString(e.getMessage());
                        }
                    }
                    break;
                case 3: //modificar una vnta
                    try {
                        this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                        this.update();
                        break;
                    } catch (InvalidIdNotFound e) {
                        Consola.soutString(e.getMessage());
                    }
                    break;
                case 4://remover
                    try {
                        this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                        this.remover();
                        break;
                    } catch (InvalidIdNotFound e) {
                        Consola.soutString(e.getMessage());
                    }
                    break;
                case 5: //mostrar todas
                    this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                    break;
                default:
                    Consola.soutString("ingresar un dato valido");
                    break;
            }

        }
        while (eleccion!=0);

    }
}