package com.framallo90.Venta.Model.Entity;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venta {
    private final Integer idVenta;
    private static Integer cont = 0;
    private Empleados empleados;
    private Comprador comprador;
    private Automovil automovil;
    private String fecha;
    private MetodoDePago transaccion;
    public Venta(Empleados empleados, Comprador comprador, Automovil automovil, LocalDate fecha, MetodoDePago transaccion) {
        this.idVenta = ++cont;
        this.empleados = empleados;
        this.comprador = comprador;
        this.automovil = automovil;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = formatter.format(LocalDate.now());
        this.transaccion = transaccion;
    }
    public Integer getIdVenta() {
        return idVenta;
    }
    public static Integer getCont() {
        return cont;
    }
    public static void setCont(Integer cont) {
        Venta.cont = cont;
    }
    public Empleados getEmpleados() {
        return empleados;
    }
    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }
    public Comprador getComprador() {
        return comprador;
    }
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
    public Automovil getAutomovil() {
        return automovil;
    }
    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }
    public String getFecha() {
        return fecha;
    }
    public MetodoDePago getTransaccion() {
        return transaccion;
    }
    public void setMetodoDePago(MetodoDePago transaccion) {
        this.transaccion = transaccion;
    }
    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", empleados=" + empleados +
                ", comprador=" + comprador +
                ", automovil=" + automovil +
                ", fecha=" + fecha +
                ", transaccion=" + transaccion +
                '}';
    }
}