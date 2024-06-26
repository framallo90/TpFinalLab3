
package com.framallo90.Automovil.View;

import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.consola.Consola;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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


        public void mostrarAuto(Automovil automovil){
            System.out.println("----------");
            System.out.println("- ID..........: " + automovil.getId());
            System.out.println("- Patente.....: " + automovil.getPatente());
            System.out.println("- Marca.......: " + automovil.getMarca());
            System.out.println("- Modelo......: " + automovil.getModelo());
            System.out.println("- Anio........: " + automovil.getAnio());
            BigDecimal bigDecimal = new BigDecimal(automovil.getPrecio());
            System.out.println("- Precio......: $" + bigDecimal.toPlainString());
            System.out.println("----------");

        }
    /**
     * Muestra el stock actual de vehículos.
     * @param automovilList Lista de Automovil que se desea mostrar.

     */
        public void mostrarAutomoviles(List<Automovil> automovilList)  {
            /**
             * Método que muestra el stock actual de vehículos, recibiendo por parámetro la lista cargada (o no).
             * Si la lista está vacía, se lanza una excepción EmptyAStockException.
             * Cuando la lista tiene elementos, se itera con un bucle for-each mostrando cada automóvil.
             * Finalmente, se informa acerca del contador de automóviles en stock.
             */


            automovilList.forEach(a->mostrarAuto(a));

        }

    public void buscarAutomovilesXFiltro(List<Automovil> lista)  {
        Predicate<Automovil>[] arrayCondiciones = new Predicate[5];
        String[] arrayTagsMostrar = new String[5];
        int cont = 0;
        int opc = -1;
        mostrarAutomoviles(lista);
        do {
            System.out.println("-> LISTA DE AUTOMÓVILES: " +
                    "\n1. Filtrar por Marca" +
                    "\n2. Filtrar por Modelo" +
                    "\n3. Filtrar por Año" +
                    "\n4. Establecer Precio máximo" +
                    "\n5. Establecer Precio mínimo"
            );
            if (cont > 0) {
                System.out.println("6. Quitar Filtro");
            }
            System.out.println("0. Volver");
            opc = Consola.ingresarXInteger("una opción para Filtrar");
            switch (opc) {
                case 1:
                    String marca = ingresoMarca();
                    arrayCondiciones[0] = m -> m.getMarca().toLowerCase().contains(marca.toLowerCase());
                    arrayTagsMostrar[0] = "Marca " + marca + " | ";
                    break;
                case 2:
                    String modelo = ingresoModelo();
                    arrayCondiciones[1] = m -> m.getModelo().toLowerCase().contains(modelo.toLowerCase());
                    arrayTagsMostrar[1] = "Modelo " + modelo + " | ";
                    break;
                case 3:
                    Integer anio = ingresoAnio();
                    arrayCondiciones[2] = a -> a.getAnio().equals(anio);
                    arrayTagsMostrar[2] = "Año " + anio.toString() + " | ";
                    break;
                case 4:
                    Double max = Consola.ingresarXdouble("máximo");
                    arrayCondiciones[3] = p -> p.getPrecio() <= max;
                    arrayTagsMostrar[3] = "Precio hasta $" + max + " | ";
                    break;
                case 5:
                    Double min = Consola.ingresarXdouble("mínimo");
                    arrayCondiciones[4] = p -> p.getPrecio() >= min;
                    arrayTagsMostrar[4] = "Precio desde $" + min + " | ";
                    break;
                case 6:
                    if (cont > 0) {
                        System.out.println(
                                "\n1 - Filtro Marca" +
                                        "\n2 - Filtro Modelo" +
                                        "\n3 - Filtro Año" +
                                        "\n4 - Filtro Precio máximo" +
                                        "\n5 - Filtro Precio mínimo" +
                                        "\n0 - Volver"
                        );
                        Integer sacar = Consola.ingresarXInteger("una opcion");
                        if (6 > sacar && sacar > 0) {
                            arrayCondiciones[sacar - 1] = null;
                        }
                    } else {
                        Consola.soutAlertString("Opción Invalida!.");
                    }
                    break;
                case 0:
                    // Salir
                    break;
                default:
                    opc = -1;
                    Consola.soutAlertString("Opción Invalida!.");
                    break;
            }
            List<Predicate<Automovil>> listaFiltros = new ArrayList<>();
            for (Predicate<Automovil> p : arrayCondiciones) {
                if (p != null) {
                    listaFiltros.add(p);
                }
            }
            cont = listaFiltros.size();
            if (opc != 0) {
                if (cont > 0) {
                    // Muestra los filtros aplicados
                    System.out.println("Filtros:");
                    System.out.printf("\033[36m | ");
                    for (int i = 0; i < 5; i++) {
                        if (arrayCondiciones[i] != null) {
                            System.out.printf(arrayTagsMostrar[i]);
                        }
                    }
                    System.out.println("\u001B[0m");
                    // Procesa los datos y muestra las coincidencias
                    Integer coincidencias = 0;
                    if (cont == 1) {
                        coincidencias = (int) lista.stream()
                                .filter(listaFiltros.get(0))
                                .peek(a-> mostrarAuto(a))
                                .count();
                    } else if (cont == 2) {
                        coincidencias = (int) lista.stream()
                                .filter(listaFiltros.get(0))
                                .filter(listaFiltros.get(1))
                                .peek(a-> mostrarAuto(a))
                                .count();
                    } else if (cont == 3) {
                        coincidencias = (int) lista.stream()
                                .filter(listaFiltros.get(0))
                                .filter(listaFiltros.get(1))
                                .filter(listaFiltros.get(2))
                                .peek(a-> mostrarAuto(a))
                                .count();
                    } else if (cont == 4) {
                        coincidencias = (int) lista.stream()
                                .filter(listaFiltros.get(0))
                                .filter(listaFiltros.get(1))
                                .filter(listaFiltros.get(2))
                                .filter(listaFiltros.get(3))
                                .peek(a-> mostrarAuto(a))
                                .count();
                    } else if (cont == 5) {
                        coincidencias = (int) lista.stream()
                                .filter(arrayCondiciones[0])
                                .filter(arrayCondiciones[1])
                                .filter(arrayCondiciones[2])
                                .filter(arrayCondiciones[3])
                                .filter(arrayCondiciones[4])
                                .peek(a-> mostrarAuto(a))
                                .count();
                    }
                    // Avisa si no hay coincidencias
                    if (coincidencias == 0) {
                        Consola.soutAlertString("NO hay Automóviles que coincidan con los Filtros.");
                    }
                } else {
                    // Muestra la lista sin filtros
                    if (lista.isEmpty()) {
                        Consola.soutAlertString("NO hay Automóviles registrados.");
                    } else {
                       mostrarAutomoviles(lista);
                    }
                }
            }
        } while (opc != 0);
    }
        /**
         * Método para ingresar la marca de un automóvil.
         * @return La marca ingresada por el usuario.
         */
        public String ingresoMarca() {
            return Consola.ingresarXString("la Marca");
        }

        /**
         * Método para ingresar el modelo de un automóvil.
         * @return El modelo ingresado por el usuario.
         */
        public String ingresoModelo() {
            return Consola.ingresarXStringSimple("el Modelo");
        }

        /**
         * Método para ingresar el precio de un automóvil.
         * @return El precio ingresado por el usuario.
         */
        public Double ingresoPrecio() {
            return Consola.ingresarXdouble("el Precio");
        }

        /**
         * Método para ingresar la patente de un automóvil.
         * @return La patente ingresada por el usuario.
         */
        public String ingresoPatente() {
            return Consola.patente("la Patente");
        }

        /**
         * Método para ingresar el año de un automóvil.
         * @return El año ingresado por el usuario.
         */
        public Integer ingresoAnio() {
            return Consola.ingresarXInteger("el Anio");
        }

        /**
         * Imprime el menú de opciones para administradores relacionado con los automóviles.
         */
        public void printMenuAutomovilAdmin() {
            System.out.println("""
                 --- MENU AUTOMOVIL (administrador) ---
                1. Agregar Automovil
                2. Eliminar Automovil
                3. Buscar Automovil
                4. Listado de Automoviles (con filtro)
                
                0. Volver
                ---------------------------------------
                """);
        }

        /**
         * Imprime el menú de opciones para vendedores relacionado con los automóviles.
         */
        public void printMenuAutomovilVendedor() {
            System.out.println("""
                --- MENU AUTOMOVIL (vendedor) ---
                1. Buscar Automovil
                2. Lista Automoviles
                3. Lista Automoviles (con filtro)
                
                0. Volver
                ---------------------------------""");
        }
    }