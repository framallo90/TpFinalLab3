package com.framallo90.Comprador.Controller;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Comprador.Model.Repository.CompradorRepository;
import com.framallo90.Comprador.View.CompradorView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;

public class CompradorController {
    CompradorView compradorView;
    CompradorRepository compradorRepository;

    public CompradorController(CompradorView compradorView, CompradorRepository compradorRepository) {
        this.compradorView = compradorView;
        this.compradorRepository = compradorRepository;
    }

    public void add(){
        String nombre = Consola.ingresarXString("nombre");
        String apellido = Consola.ingresarXString("apellido");
        Integer dni = Consola.ingresarXInteger("dni");
        String email = compradorView.ingresoEmail();

        Comprador comprador = new Comprador(nombre,apellido,dni,email);
    }

    public void remove(){
        try {
            compradorRepository.remove(String.valueOf(Consola.ingresarXInteger("id")));
        }catch (InvalidIdNotFound e) {
            System.out.println(e.getMessage());
        }
    }
    /// terminar
    public void update()
    {

    }
    public void show()
    {

    }
}
