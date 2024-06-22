package com.framallo90.Automovil.Model.Entity;

/**
 * Clase que representa un automóvil con atributos como marca, modelo, precio, patente y año.
 */
public class Automovil {
    private Integer id;
    private static Integer cont = 0;
    private String marca, modelo;
    private Double precio;
    private String patente;
    private Integer anio;

    /**
     * Constructor para inicializar un objeto Automovil con los atributos especificados.
     * @param marca Marca del automóvil.
     * @param modelo Modelo del automóvil.
     * @param precio Precio del automóvil.
     * @param patente Patente del automóvil.
     * @param anio Año de fabricación del automóvil.
     */
    public Automovil(String marca, String modelo, Double precio, String patente, Integer anio) {
        this.id = ++cont;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.patente = patente;
        this.anio = anio;
    }

    /**
     * Obtiene la patente del automóvil.
     * @return Patente del automóvil.
     */
    public String getPatente() {
        return patente;
    }

    /**
     * Establece la patente del automóvil.
     * @param patente Nueva patente a establecer.
     */
    public void setPatente(String patente) {
        this.patente = patente;
    }

    /**
     * Obtiene el ID del automóvil.
     * @return ID del automóvil.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Obtiene el contador estático de automóviles creados.
     * @return Contador de automóviles creados.
     */
    public static Integer getCont() {
        return cont;
    }

    /**
     * Establece el contador estático de automóviles creados.
     * @param cont Nuevo valor para el contador de automóviles.
     */
    public static void setCont(Integer cont) {
        Automovil.cont = cont;
    }

    /**
     * Obtiene la marca del automóvil.
     * @return Marca del automóvil.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del automóvil.
     * @param marca Nueva marca a establecer.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el modelo del automóvil.
     * @return Modelo del automóvil.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo del automóvil.
     * @param modelo Nuevo modelo a establecer.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el precio del automóvil.
     * @return Precio del automóvil.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el ID del automóvil.
     * @param id Nuevo ID a establecer.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Establece el precio del automóvil.
     * @param precio Nuevo precio a establecer.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el año de fabricación del automóvil.
     * @return Año de fabricación del automóvil.
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * Establece el año de fabricación del automóvil.
     * @param anio Nuevo año de fabricación a establecer.
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * Override del método toString para obtener una representación en cadena del objeto Automovil.
     * @return Representación en cadena del objeto Automovil.
     */
    @Override
    public String toString() {
        return "Automovil{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio='" + anio + '\'' +
                ", precio=" + precio +
                ", patente='" + patente + '\'' +
                '}';
    }
}
