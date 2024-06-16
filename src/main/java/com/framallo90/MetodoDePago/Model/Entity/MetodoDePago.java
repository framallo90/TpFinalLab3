package com.framallo90.MetodoDePago.Model.Entity;

public class MetodoDePago {
    private String tipo; /// Credito / Contado
    private Integer cuotas;
    private Double precioFinanciado;
    public final static Double INTERES_MENSUAL = 1.63d;

    public MetodoDePago(String tipo, Integer cuotas, Double precioFinanciado) {
        this.tipo = tipo;
        this.cuotas = cuotas;
        this.precioFinanciado = precioFinanciado;
    }
    @Override
    public String toString() {
        return tipo.toUpperCase() + ", CANT. CUOTAS: " + cuotas + ". Precio final (financiado): " + precioFinanciado;
    }
}
