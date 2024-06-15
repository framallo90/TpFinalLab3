package com.framallo90.Automovil.Controller;

import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;

public class AutomovilController {
    private AutomovilRepository automovilRepository;
    private AutomovilView automovilView;

    public AutomovilController(AutomovilRepository automovilRepository, AutomovilView automovilView) {
        this.automovilRepository = automovilRepository;
        this.automovilView = automovilView;
    }
    public void crearAutomovil()
    {
        Automovil nuevo = new Automovil(this.automovilView.ingresoMarca(),
                this.automovilView.ingresoModelo(),this.automovilView.ingresoPrecio(),this.automovilView.ingresoPatente());

        this.automovilRepository.add(nuevo);
    }
    public void borrarAutomovil()
    {
        Integer id = Consola.ingresarXInteger("ID: ");
        try {
            this.automovilRepository.remove(id);
        }catch (InvalidIdNotFound e) {
            System.out.println(e.getMessage());
        }
    }
    public void modificarAutomovil()
    {
        /// La unica que falta,
    }
    public void mostrarAutomoviles()
    {
        this.automovilView.mostrarAutomoviles(this.automovilRepository.getAutomovilList());
    }

}
