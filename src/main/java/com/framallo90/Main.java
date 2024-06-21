package com.framallo90;
import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Repository.CompradorRepository;
import com.framallo90.Comprador.View.CompradorView;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Empleados.Model.Repository.EmpleadosRepository;
import com.framallo90.Empleados.View.EmpleadosView;
import com.framallo90.MetodoDePago.Controller.MetodoController;
import com.framallo90.MetodoDePago.View.MetodoView;
import com.framallo90.Venta.Controller.VentaController;
import com.framallo90.Venta.Model.Repository.VentaRepository;
import com.framallo90.Venta.View.VentaView;
public class Main {
    public static void main(String[] args) {
        CompradorView compradorView = new CompradorView();
        CompradorRepository compradorRepository = new CompradorRepository();
        CompradorController compradorController = new CompradorController(compradorView,compradorRepository);
        EmpleadosView empleadosView = new EmpleadosView();
        EmpleadosRepository empleadosRepository = new EmpleadosRepository();
        EmpleadosController empleadosController = new EmpleadosController(empleadosRepository,empleadosView);
        MetodoView metodoView = new MetodoView();
        MetodoController metodoController = new MetodoController(metodoView);
        AutomovilView automovilView = new AutomovilView();
        AutomovilRepository automovilRepository = new AutomovilRepository();
        AutomovilController automovilController = new AutomovilController(automovilRepository,automovilView);
        VentaView ventaView = new VentaView();
        VentaRepository ventaRepository = new VentaRepository();
        VentaController ventaController = new VentaController(empleadosController,compradorController,automovilController,metodoController,ventaView,ventaRepository);
        GestionConsecionaria gestionConsecionaria = new GestionConsecionaria(compradorView,compradorRepository,compradorController,empleadosView
        ,empleadosRepository,empleadosController,metodoView,metodoController,automovilView,
                automovilRepository,automovilController,ventaView,ventaRepository,ventaController);
        gestionConsecionaria.iniciar();
    }
}