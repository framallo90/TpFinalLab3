package com.framallo90.Automovil.Model.Entity;

public class Automovil {
    private Integer id;
    private static Integer cont = 0;
    private String marca,modelo;
    private Double precio;
    private String patente;

    public Automovil(String marca, String modelo, Double precio,String patente) {
        this.id = ++cont;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.patente = patente;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Automovil{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", patente='" + patente + '\'' +
                '}';
    }
}
