package com.framallo90.MetodoDePago.Model.Entity;
/**
 * Clase que representa un método de pago para transacciones comerciales.
 * Permite definir y gestionar los detalles del método de pago, incluyendo el tipo (crédito o contado),
 * cantidad de cuotas y precio financiado.
 *
 * Ejemplo de uso:
 * ```
 * MetodoDePago metodo = new MetodoDePago("Crédito", 12, 15000.0);
 * System.out.println(metodo.toString());
 * ```
 * Salida esperada: "CRÉDITO, CANT. CUOTAS: 12. Precio final (financiado): 15000.0"
 *
 * @author Framallo
 * @version 1.0
 */
public class MetodoDePago {
    private String tipo; // Credito / Contado
    private Integer cuotas;
    private Double precioFinanciado;
    public final static Double INTERES_MENSUAL = 1.63d;

    /**
     * Constructor que inicializa un método de pago con los parámetros proporcionados.
     *
     * @param tipo            Tipo de método de pago (crédito o contado).
     * @param cuotas          Cantidad de cuotas para el pago financiado.
     * @param precioFinanciado Precio final financiado del producto o servicio.
     */
    public MetodoDePago(String tipo, Integer cuotas, Double precioFinanciado) {
        this.tipo = tipo;
        this.cuotas = cuotas;
        this.precioFinanciado = precioFinanciado;
    }

    /**
     * Obtiene el tipo de método de pago (crédito o contado).
     *
     * @return El tipo de método de pago.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de método de pago (crédito o contado).
     *
     * @param tipo El tipo de método de pago a establecer.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la cantidad de cuotas para el pago financiado.
     *
     * @return La cantidad de cuotas.
     */
    public Integer getCuotas() {
        return cuotas;
    }

    /**
     * Establece la cantidad de cuotas para el pago financiado.
     *
     * @param cuotas La cantidad de cuotas a establecer.
     */
    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * Obtiene el precio final financiado del producto o servicio.
     *
     * @return El precio final financiado.
     */
    public Double getPrecioFinanciado() {
        return precioFinanciado;
    }

    /**
     * Establece el precio final financiado del producto o servicio.
     *
     * @param precioFinanciado El precio final financiado a establecer.
     */
    public void setPrecioFinanciado(Double precioFinanciado) {
        this.precioFinanciado = precioFinanciado;
    }

    /**
     * Representación en formato de cadena del método de pago.
     *
     * @return Cadena que describe el tipo de método de pago, cantidad de cuotas y precio final financiado.
     */
    @Override
    public String toString() {
        return tipo.toUpperCase() + ", CANT. CUOTAS: " + cuotas + ". Precio final (financiado): " + precioFinanciado;
    }
}
