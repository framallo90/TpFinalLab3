
package com.framallo90.Automovil.View;

import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Excepciones.EmptyAStockException;
import com.framallo90.consola.Consola;

import java.util.List;

    /**
     * Vista para la interacción con la gestión de Automoviles en el sistema.
     * Proporciona métodos para mostrar información, ingresar datos y mostrar menús.
     */
    public class AutomovilView {

        /**
         * Constructor por defecto de la clase AutomovilView.
         */
        public AutomovilView() {
        }

        /**
         * Muestra el stock actual de vehículos.
         * @param automovilList Lista de Automovil que se desea mostrar.
         * @throws EmptyAStockException Si la lista de automóviles está vacía.
         */
        public void mostrarAutomoviles(List<Automovil> automovilList) throws EmptyAStockException {
            /**
             * Método que muestra el stock actual de vehículos, recibiendo por parámetro la lista cargada (o no).
             * Si la lista está vacía, se lanza una excepción EmptyAStockException.
             * Cuando la lista tiene elementos, se itera con un bucle for-each mostrando cada automóvil.
             * Finalmente, se informa acerca del contador de automóviles en stock.
             */
            if (automovilList == null || automovilList.isEmpty()) {
                throw new EmptyAStockException("La lista está vacía.");
            }

            for (Automovil automovil : automovilList)
            {
                System.out.println("===============================================");
                System.out.println("Patente: " + automovil.getPatente());
                System.out.println("Marca: " + automovil.getMarca());
                System.out.println("Modelo: " + automovil.getModelo());
                System.out.println("Anio: " + automovil.getAnio());
                System.out.println("Precio: $" + automovil.getPrecio());
                System.out.println("===============================================");
            }
            System.out.println("Total de vehículos en stock -> " + Automovil.getCont());
        }

        /**
         * Método para ingresar la marca de un automóvil.
         * @return La marca ingresada por el usuario.
         */
        public String ingresoMarca() {
            return Consola.ingresarXString("marca");
        }

        /**
         * Método para ingresar el modelo de un automóvil.
         * @return El modelo ingresado por el usuario.
         */
        public String ingresoModelo() {
            return Consola.ingresarXStringSimple("modelo");
        }

        /**
         * Método para ingresar el precio de un automóvil.
         * @return El precio ingresado por el usuario.
         */
        public Double ingresoPrecio() {
            return Consola.ingresarXdouble("precio");
        }

        /**
         * Método para ingresar la patente de un automóvil.
         * @return La patente ingresada por el usuario.
         */
        public String ingresoPatente() {
            return Consola.patente("patente");
        }

        /**
         * Método para ingresar el año de un automóvil.
         * @return El año ingresado por el usuario.
         */
        public Integer ingresoAnio() {
            return Consola.ingresarXInteger("anio");
        }

        /**
         * Imprime el menú de opciones para administradores relacionado con los automóviles.
         */
        public void printMenuAutomovilAdmin() {
            System.out.println("""
                --- MENU AUTOMOVIL (administrador) ---
                1. Agregar 
                2. Borrar 
                3. Buscar automovil
                4. Lista automoviles (con filtro)
                0. Volver""");
        }

        /**
         * Imprime el menú de opciones para vendedores relacionado con los automóviles.
         */
        public void printMenuAutomovilVendedor() {
            System.out.println("""
                --- MENU AUTOMOVIL (vendedor) ---
                1. Buscar automovil
                2. Lista automoviles
                3. Lista automoviles (con filtro)
                0. Volver""");
        }
    }