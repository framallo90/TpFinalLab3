package com.framallo90.Venta.Model.Entity;

import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representa una venta realizada de un automóvil a un comprador por parte de un empleado,
 * con un método de pago específico y una fecha de transacción.
 */
public class Venta {
    private final Integer idVenta; // Identificador único de la venta
    private static Integer cont = 0; // Contador estático para generar IDs de venta incrementalmente
    private Empleados empleados; // Empleado que realiza la venta
    private Comprador comprador; // Comprador que realiza la compra
    private Automovil automovil; // Automóvil vendido
    private String fecha; // Fecha de la venta
    private MetodoDePago transaccion; // Método de pago utilizado para la transacción

    /**
     * Constructor de la clase Venta.
     *
     * @param empleados Empleado que realiza la venta.
     * @param comprador Comprador que realiza la compra.
     * @param automovil Automóvil vendido en la venta.
     * @param fecha Fecha de la venta.
     * @param transaccion Método de pago utilizado para la transacción.
     */
    public Venta(Empleados empleados, Comprador comprador, Automovil automovil, LocalDate fecha, MetodoDePago transaccion) {
        this.idVenta = ++cont; // Incrementa el contador y asigna el ID único de la venta
        this.empleados = empleados;
        this.comprador = comprador;
        this.automovil = automovil;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = formatter.format(LocalDate.now()); // Formatea la fecha actual en formato dd/MM/yyyy
        this.transaccion = transaccion;
    }

    /**
     * Obtiene el ID único de la venta.
     *
     * @return El ID único de la venta.
     */
    public Integer getIdVenta() {
        return idVenta;
    }

    /**
     * Obtiene el contador estático utilizado para generar IDs de venta incrementalmente.
     *
     * @return El valor actual del contador estático.
     */
    public static Integer getCont() {
        return cont;
    }

    /**
     * Establece el valor del contador estático utilizado para generar IDs de venta.
     *
     * @param cont El nuevo valor del contador estático.
     */
    public static void setCont(Integer cont) {
        Venta.cont = cont;
    }

    /**
     * Obtiene el empleado que realizó la venta.
     *
     * @return El objeto Empleados que representa al empleado vendedor.
     */
    public Empleados getEmpleados() {
        return empleados;
    }

    /**
     * Establece el empleado que realizó la venta.
     *
     * @param empleados El nuevo empleado que realizó la venta.
     */
    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    /**
     * Obtiene el comprador que realizó la compra.
     *
     * @return El objeto Comprador que representa al comprador de la venta.
     */
    public Comprador getComprador() {
        return comprador;
    }

    /**
     * Establece el comprador que realizó la compra.
     *
     * @param comprador El nuevo comprador que realizó la compra.
     */
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    /**
     * Obtiene el automóvil vendido en la venta.
     *
     * @return El objeto Automovil que representa al automóvil vendido.
     */
    public Automovil getAutomovil() {
        return automovil;
    }

    /**
     * Establece el automóvil vendido en la venta.
     *
     * @param automovil El nuevo automóvil vendido en la venta.
     */
    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }

    /**
     * Obtiene la fecha de la venta.
     *
     * @return La fecha de la venta en formato String (dd/MM/yyyy).
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Obtiene el método de pago utilizado en la transacción de la venta.
     *
     * @return El objeto MetodoDePago que representa el método de pago utilizado.
     */
    public MetodoDePago getTransaccion() {
        return transaccion;
    }

    /**
     * Establece el método de pago utilizado en la transacción de la venta.
     *
     * @param transaccion El nuevo método de pago utilizado en la transacción.
     */
    public void setMetodoDePago(MetodoDePago transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * Retorna una representación String de la venta, mostrando todos sus atributos.
     *
     * @return Una cadena que representa los atributos de la venta.
     */
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
