package com.framallo90.Automovil.Controller;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;
public class AutomovilController {
    private final AutomovilRepository automovilRepository;
    private final AutomovilView automovilView;
    public AutomovilController(AutomovilRepository automovilRepository, AutomovilView automovilView) {
        this.automovilRepository = automovilRepository;
        this.automovilView = automovilView;
    }
    /**
     * Controlador principal para el manejo de los automóviles en la concesionaria
     * Permite agregar nuevos automóviles al stock, listar los vehículos en stock,
     * modificar aspectos de vehículos, borrarlos del stock.
     */
    public void agregarAutomovilAlStock()
    {
        Automovil nuevo = new Automovil(this.automovilView.ingresoMarca(),
                this.automovilView.ingresoModelo(),this.automovilView.ingresoPrecio(),this.automovilView.ingresoPatente(),this.automovilView.ingresoAnio());
        /// Carga de automovil

        this.automovilRepository.add(nuevo); // lo añadimos
    }
    public void borrarAutomovilEnStock()
    {
        try {
            this.automovilRepository.remove(Consola.ingresarXInteger("ID"));
        }catch (InvalidIdNotFound e) {
            System.out.println(e.getMessage());
        }
        /**
         * Manejamos el caso de que el ID no este en la lista
         */
    }
    public void modificarAutomovilEnStock()
    {
        try {
            this.automovilRepository.update(Consola.ingresarXInteger("ID"));
        }catch (InvalidIdNotFound e)
        {
            System.out.println(e.getMessage());
        }
        /**
         * Manejamos el caso de que el ID no este en la lista
         */
    }
    //SOBRECARGA
    public void modificarAutomovilEnStock(Automovil automovil)
    {
        try {
            this.automovilRepository.update(automovil.getId());
        }catch (InvalidIdNotFound e)
        {
            System.out.println(e.getMessage());
        }
        /**
         * Manejamos el caso de que el ID no este en la lista
         */
    }
    public void mostrarAutomovilesEnStock()
    {
        this.automovilView.mostrarAutomoviles(this.automovilRepository.getAutomovilList());
        ///Mostramos la lista
    }
    public Automovil find(Integer id){
        return this.automovilRepository.find(id);
    }
}