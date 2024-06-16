package com.framallo90.MetodoDePago.Controller;

import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.MetodoDePago.View.MetodoView;

public class MetodoController {
    private MetodoView metodoView;

    public MetodoController(MetodoView metodoView) {
        this.metodoView = metodoView;
    }
    public MetodoDePago cargarMDP(Double precioVehiculo) {
        return metodoView.cargarMetodoDP(precioVehiculo);
    }
}
