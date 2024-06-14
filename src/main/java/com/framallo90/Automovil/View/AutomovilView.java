package com.framallo90.Automovil.View;

import com.framallo90.consola.Consola;

public class AutomovilView {

    public String ingresoMarca(){
        return Consola.ingresarXString("marca");
    }

    public String ingresoModelo(){
        return Consola.ingresarXString("modelo");
    }

    public Double ingresoPrecio(){
        return Consola.ingresarXdouble("precio");
    }

}
