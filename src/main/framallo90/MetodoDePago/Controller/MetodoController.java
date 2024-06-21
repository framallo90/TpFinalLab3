package com.framallo90.MetodoDePago.Controller;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.MetodoDePago.View.MetodoView;
import com.framallo90.consola.Consola;
public class MetodoController {
    private MetodoView metodoView;

    public MetodoController(MetodoView metodoView) {
        this.metodoView = metodoView;
    }
    public MetodoDePago cargarMDP(Double precioVehiculo) {
        return metodoView.cargarMetodoDP(precioVehiculo);
    }
    public void updateMDP(MetodoDePago metodoDePago, Double precioVehiculo){
        Integer eleccion;
        String tipo = metodoDePago.getTipo();
        this.metodoView.printMenuModifMDP();
        eleccion=Consola.ingresarXInteger("eleccion");
        if (eleccion==2) return;
        else if (eleccion==1)
            metodoDePago=this.cargarMDP(precioVehiculo);
    }

}
