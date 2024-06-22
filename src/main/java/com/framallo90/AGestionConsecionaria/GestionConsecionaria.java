package com.framallo90.AGestionConsecionaria;
import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Repository.CompradorRepository;
import com.framallo90.Comprador.View.CompradorView;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Empleados.Model.Repository.EmpleadosRepository;
import com.framallo90.Empleados.View.EmpleadosView;
import com.framallo90.Login.Login;
import com.framallo90.MetodoDePago.Controller.MetodoController;
import com.framallo90.MetodoDePago.View.MetodoView;
import com.framallo90.Venta.Controller.VentaController;
import com.framallo90.Venta.Model.Repository.VentaRepository;
import com.framallo90.Venta.View.VentaView;
import com.framallo90.consola.Consola;
public class GestionConsecionaria {
    private CompradorView compradorView;
    private CompradorRepository compradorRepository;
    private CompradorController compradorController;
    private EmpleadosView empleadosView;
    private EmpleadosRepository empleadosRepository;
    private EmpleadosController empleadosController;
    private MetodoView metodoView;
    private MetodoController metodoController;
    private AutomovilView automovilView ;
    private AutomovilRepository automovilRepository;
    private AutomovilController automovilController;
    private VentaView ventaView;
    private VentaRepository ventaRepository;
    private VentaController ventaController;
    public GestionConsecionaria(CompradorView compradorView, CompradorRepository compradorRepository, CompradorController compradorController, EmpleadosView empleadosView, EmpleadosRepository empleadosRepository, EmpleadosController empleadosController, MetodoView metodoView, MetodoController metodoController, AutomovilView automovilView, AutomovilRepository automovilRepository, AutomovilController automovilController, VentaView ventaView, VentaRepository ventaRepository, VentaController ventaController) {
        this.compradorView = compradorView;
        this.compradorRepository = compradorRepository;
        this.compradorController = compradorController;
        this.empleadosView = empleadosView;
        this.empleadosRepository = empleadosRepository;
        this.empleadosController = empleadosController;
        this.metodoView = metodoView;
        this.metodoController = metodoController;
        this.automovilView = automovilView;
        this.automovilRepository = automovilRepository;
        this.automovilController = automovilController;
        this.ventaView = ventaView;
        this.ventaRepository = ventaRepository;
        this.ventaController = ventaController;
    }
    public void iniciar(){
        Consola.soutString("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@&&&&&@@&&&&&@@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@&PB&&BGGGG#&&BG&@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@&#&###&&&&##&&&&&&&&&&@@@@@@@@@@@\n" +
                "@@@@@@@@@#P5YYYP#####BB###&&GPPPGG&@@@@@@@@@\n" +
                "@@@@@@@@@GY5PP5G@&&&&&&&&&&@#PGPGP#@@@@@@@@@\n" +
                "@@@@@@@@@&GBGBBG@&&&&&&&&&@@B##B#B&@@@@@@@@@\n" +
                "@@@@@@@@@&&##BBB@@@@@@@@@@@@####&&&@@@@@@@@@\n" +
                "@@@@@@@@@&&@&&&&@@@@@@@@@@@@&@@@@@@@@@@@@@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Login login = new Login();
        Empleados  empleadoIngresado = null;
        //---------------------
        int eleccion = 0;
        do {
            //LOGIN
            do{
                Consola.printMenuLogin();
                eleccion = Consola.ingresarXInteger("eleccion");
                if (eleccion == 0)
                    return;
                else if (eleccion == 1) {
                    empleadoIngresado = login.login();
                    if (empleadoIngresado == null)
                        Consola.soutString("Las credenciales son inválidas. Vuelve a intentarlo");
                }
                else System.out.println("Ingrese una opción valida (1/0). ");
            }while (empleadoIngresado == null);
                if (empleadoIngresado.getTipo().equalsIgnoreCase("admin") || empleadoIngresado.getTipo().                        equalsIgnoreCase("administrador"))
                    this.menuAdmin(empleadoIngresado);
                else if (empleadoIngresado.getTipo().equalsIgnoreCase("vendedor"))
                    this.menuVendedor(empleadoIngresado);
                else Consola.soutString("Credenciales incorrectas.");
        } while (eleccion!=0);
    }
    private void menuAdmin(Empleados empleadoIngresado){
        Integer eleccion;
        do{
            Consola.printMenuAdministrador();
            eleccion = Consola.ingresarXInteger("elección");
            switch (eleccion){
                case 0: // salir
                    empleadoIngresado = null;
                    break;
                case 1: // gestion clientes
                    compradorController.compradorMenu();
                    break;
                case 2: // gestion ventas
                    ventaController.menuVentas();
                    break;
                case 3: // gestion carros
                    automovilController.menuAutomovilAdmin();
                    break;
                case 4: // gestion usuarios
                    empleadosController.menuControllerEmpleados();
                    break;
                default: //opcion no reconocida
                    Consola.soutString("No se reconoce la opción ingresada.");
                    break;
            }
        }while (eleccion!=0);

    }
    private void menuVendedor(Empleados empleadoIngresado){
        Integer eleccion;
        do {
            Consola.printMenuVendedor();
            eleccion = Consola.ingresarXInteger("elección");
            switch (eleccion){
                case 0: // salir
                    empleadoIngresado = null;
                    break;
                case 1: // gestion clientes
                    compradorController.compradorMenu();
                    break;
                case 2: // gestion ventas
                    ventaController.menuVentas();
                    break;
                case 3: // gestion carros
                    automovilController.menuAutomovilVendedor();
            }

        }while (eleccion!=0);
    }
}