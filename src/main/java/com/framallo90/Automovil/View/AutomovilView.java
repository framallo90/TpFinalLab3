package com.framallo90.Automovil.View;

import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.consola.Consola;

import java.util.List;

public class AutomovilView {
    public AutomovilView() {
    }
    public void mostrarAutomoviles(List<Automovil> automovilList)
    {
        for (Automovil automovil : automovilList)
        {
            System.out.println(automovil.toString());
        }
    }

    public String ingresoMarca(){
        return Consola.ingresarXString("marca");
    }

    public String ingresoModelo(){
        return Consola.ingresarXString("modelo");
    }

    public Double ingresoPrecio(){
        return Consola.ingresarXdouble("precio");
    }
    public String ingresoPatente()
    {
        return Consola.patente("patente");
    }

}
