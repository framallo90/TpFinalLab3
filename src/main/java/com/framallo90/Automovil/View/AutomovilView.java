package com.framallo90.Automovil.View;

import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Excepciones.EmptyAStockException;
import com.framallo90.consola.Consola;

import java.util.List;

public class AutomovilView {
    public AutomovilView() {
    }
    public void mostrarAutomoviles(List<Automovil> automovilList) throws EmptyAStockException
    {
        /**
         * Metodo que muestra el stock actual de vehiculos, recibiendo por parametro la lista cargada (o no)
         * Si esta vacia evitaremos iterar sobre ella para avisar al usuario que la lista esta vacia
         * Cuando tenga elementos se iterará con un for-each mostrando cada automovil, finalmente se informa
         * acerca del contador de automoviles.
         */
        if (automovilList==null)
            throw new EmptyAStockException("La lista esta vacia.");

        for (Automovil automovil : automovilList)
        {
            System.out.println(automovil.toString());
        }
        System.out.println("Total de vehiculos en stock -> " + Automovil.getCont());
    }

    public String ingresoMarca(){
        return Consola.ingresarXString("marca");
    }

    public String ingresoModelo(){
        return Consola.ingresarXStringSimple("modelo");
    }

    public Double ingresoPrecio(){
        return Consola.ingresarXdouble("precio");
    }
    public String ingresoPatente()
    {
        return Consola.patente("patente");
    }
    public Integer ingresoAnio() {return Consola.ingresarXInteger("anio");
    }

    public void printMenuAutomovilAdmin(){
        System.out.println("""
                MENU AUTOMOVIL (administrador)
                1. Agregar 
                2. Borrar 
                3. Modificar
                4. Buscar automovil
                5. Lista automoviles
                6. Lista automoviles (con filtro)
                0. Atras""");
    }
    public void printMenuAutomovilVendedor(){
        System.out.println("""
                MENU AUTOMOVIL (vendedor)
                1. Buscar automovil
                2. Lista automoviles
                3. Lista automoviles (con filtro)
                0. Atras""");
    }
}
