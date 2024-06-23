package com.framallo90;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
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
import com.framallo90.MetodoDePago.Controller.MetodoController;
import com.framallo90.MetodoDePago.View.MetodoView;
import com.framallo90.Venta.Controller.VentaController;
import com.framallo90.Venta.Model.Repository.VentaRepository;
import com.framallo90.Venta.View.VentaView;


/**
 * Clase principal que inicializa y configura los componentes del sistema de gestión de concesionaria.
 *
 * <p>Esta clase se encarga de instanciar todos los controladores, vistas y repositorios necesarios
 * para el funcionamiento de la aplicación. Luego, crea una instancia de {@link GestionConsecionaria}
 * y llama al método {@link GestionConsecionaria#iniciar()} para arrancar la aplicación.
 */
public class Main {

    /**
     * Método principal de la aplicación que inicializa y configura todos los componentes.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en esta implementación).
     */
    public static void main(String[] args) {
        // Creación de instancias de las vistas, repositorios y controladores necesarios
        CompradorView compradorView = new CompradorView();
        CompradorRepository compradorRepository = new CompradorRepository();
        CompradorController compradorController = new CompradorController(compradorView, compradorRepository);

        EmpleadosView empleadosView = new EmpleadosView();
        EmpleadosRepository empleadosRepository = new EmpleadosRepository();
        EmpleadosController empleadosController = new EmpleadosController(empleadosRepository, empleadosView);

        MetodoView metodoView = new MetodoView();
        MetodoController metodoController = new MetodoController(metodoView);

        AutomovilView automovilView = new AutomovilView();
        AutomovilRepository automovilRepository = new AutomovilRepository();
        AutomovilController automovilController = new AutomovilController(automovilRepository, automovilView);

        VentaView ventaView = new VentaView();
        VentaRepository ventaRepository = new VentaRepository();
        VentaController ventaController = new VentaController(empleadosController, compradorController,
                automovilController, metodoController, ventaView, ventaRepository);

        // Creación de la instancia de la clase principal de gestión de concesionaria
        GestionConsecionaria gestionConsecionaria = new GestionConsecionaria(compradorController, empleadosController,
                metodoController, automovilController, ventaController);

        // Inicia la aplicación llamando al método iniciar de GestionConsecionaria
        gestionConsecionaria.iniciar();
    }
}
