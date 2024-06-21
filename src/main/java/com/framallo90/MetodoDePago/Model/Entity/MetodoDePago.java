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
    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return tipo.toUpperCase() + ", CANT. CUOTAS: " + cuotas + ". Precio final (financiado): " + precioFinanciado;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Integer getCuotas() {
        return cuotas;
    }
    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }
    public Double getPrecioFinanciado() {
        return precioFinanciado;
    }
    public void setPrecioFinanciado(Double precioFinanciado) {
        this.precioFinanciado = precioFinanciado;
    }
}