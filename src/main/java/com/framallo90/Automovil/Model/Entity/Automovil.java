package com.framallo90.Automovil.Model.Entity;

public class Automovil {
    private Integer id;
    private static Integer cont = 0;
    private String marca,modelo;
    private Double precio;
    private String patente;

    public Automovil(String marca, String modelo, Double precio) {
        this.id = ++cont;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }


    public static Integer getCont() {
        return cont;
    }

    public static void setCont(Integer cont) {
        Automovil.cont = cont;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
