package com.framallo90.Automovil.Controller;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AutomovilController {
    private final AutomovilRepository automovilRepository;
    private final AutomovilView automovilView;
    public AutomovilController(AutomovilRepository automovilRepository, AutomovilView automovilView) {
        this.automovilRepository = automovilRepository;
        this.automovilView = automovilView;
    }
    /**
     * Controlador principal para el manejo de los automóviles en la concesionaria
     * Permite agregar nuevos automóviles al stock, listar los vehículos en stock,
     * modificar aspectos de vehículos, borrarlos del stock.
     */
    public void agregarAutomovilAlStock()
    {
        Automovil nuevo = new Automovil(this.automovilView.ingresoMarca(),
                this.automovilView.ingresoModelo(),this.automovilView.ingresoPrecio(),this.automovilView.ingresoPatente(),this.automovilView.ingresoAnio());
        /// Carga de automovil

        this.automovilRepository.add(nuevo); // lo añadimos
    }
    public void borrarAutomovilEnStock()
    {
        try {
            this.automovilRepository.remove(Consola.ingresarXInteger("ID"));
        }catch (InvalidIdNotFound e) {
            System.out.println(e.getMessage());
        }
        /**
         * Manejamos el caso de que el ID no este en la lista
         */
    }
    public void modificar()
    {
        try {
            this.automovilRepository.update(Consola.ingresarXInteger("ID"));
        }catch (InvalidIdNotFound e)
        {
            System.out.println(e.getMessage());
        }
        /**
         * Manejamos el caso de que el ID no este en la lista
         */
    }
    //SOBRECARGA
    public Automovil cambiarCoche(Automovil automovil)
    {
        Automovil retorno = this.automovilRepository.find(Consola.ingresarXInteger("ID del automocil"));
        if (retorno == null) {
            Consola.soutString("No se ha encontrado un automovil con el id ingresado");
            return automovil;
        }
        return retorno;
    }
    public void mostrarAutomovilesEnStock()
    {
        this.automovilView.mostrarAutomoviles(this.automovilRepository.getAutomovilList());
        ///Mostramos la lista
    }
    public Automovil find(Integer id){
        return this.automovilRepository.find(id);
    }

    public void buscarAutomovilesXFiltro(){

        Predicate<Automovil>[] arrayCondiciones = new Predicate[5];
        arrayCondiciones[0] = null;arrayCondiciones[1] = null;
        arrayCondiciones[2] = null;arrayCondiciones[3] = null;
        arrayCondiciones[4] = null;

        String[] arrayTagsMostrar = new String[5];
        //tendremos un maximo de 5 filtros unicos

        int cont = 0;
        int opc = -1;
        do{
            System.out.println("Menu " +
                    "\n1 - filtrar por marca" +
                    "\n2 - filtrar por modelo" +
                    "\n3 - filtrar por anio" +
                    "\n4 - establecer precio maximo" +
                    "\n5 - establecer precio minimo"
            );
            if(cont>0){System.out.println("6 - quitar filtro");}
            System.out.println("0 - atras");

            opc = Consola.ingresarXInteger("opcion");



            //se agrega/cambia/saca uno de los filtros
            switch (opc){
                case 1:
                    String marca = automovilView.ingresoMarca();
                    arrayCondiciones[0] = m -> m.getMarca().equalsIgnoreCase(marca);
                    arrayTagsMostrar[0] = "Marca " + marca + " | ";
                    break;
                case 2:
                    String modelo = automovilView.ingresoModelo();
                    arrayCondiciones[1] = m -> m.getModelo().equalsIgnoreCase(modelo);
                    arrayTagsMostrar[1] = "Modelo " + modelo + " | ";
                    break;
                case 3:
                    Integer anio = automovilView.ingresoAnio();
                    arrayCondiciones[2] = (a -> a.getAnio().equals(anio));
                    arrayTagsMostrar[2] = "Anio " + anio.toString() + " | ";
                    break;
                case 4:
                    Double max = Consola.ingresarXdouble("maximo");
                    arrayCondiciones[3] = p-> p.getPrecio() <= max;
                    arrayTagsMostrar[3] = "Precio hasta $" + max + " | ";
                    break;
                case 5:
                    Double min = Consola.ingresarXdouble("minimo");
                    arrayCondiciones[4] = p -> p.getPrecio() >= min;
                    arrayTagsMostrar[4] = "Precio desde $" + min + " | ";
                    break;
                case 6:
                    if(cont>0){
                        System.out.println("\n1 - filtro marca" +
                                "\n2 - filtro modelo" +
                                "\n3 - filtro anio" +
                                "\n4 - filtro precio maximo" +
                                "\n5 - filtro precio minimo" +
                                "\n6 - atras"
                        );
                        Integer sacar = Consola.ingresarXInteger("opcion");
                        if(6 > sacar && sacar>0){
                            arrayCondiciones[sacar-1] = null;
                        }
                    }else{
                        System.out.println("Opcion no reconocida");
                    }
                    break;
                case 0:
                    //se sale
                    break;
                default:
                    opc = -1;
                    System.out.println("Opcion no reconocida");
                    break;
            }


            //verifico el array de filtros y si tengo los paso a una lista para usarlos
            List<Predicate<Automovil>> listaFiltros = new ArrayList<>();
            for(Predicate<Automovil> p:arrayCondiciones){
                if(p != null){
                    listaFiltros.add(p);
                }
            }

            cont = listaFiltros.size();

            if(opc != 0){
                if(cont >0){///si se usan filtros
                    ///muestra los filtros que se usan
                    System.out.println("Filtros:");
                    System.out.printf("\033[36m | ");
                    for (int i = 0;i<5;i++){
                        if(arrayCondiciones[i] != null){
                            System.out.printf("\033[36m" + arrayTagsMostrar[i]);
                        }
                    }
                    System.out.println("\u001B[0m");
                    //procesa los datos y muestra si hay coincidencias o no
                    Integer coincidencias = 0;
                    if (cont == 1) {
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(listaFiltros.get(0))
                                .peek(System.out::println)
                                .count();

                    } else if( cont == 2){
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(listaFiltros.get(0))
                                .filter(listaFiltros.get(1))
                                .peek(System.out::println)
                                .count();
                    } else if(cont == 3) {
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(listaFiltros.get(0))
                                .filter(listaFiltros.get(1))
                                .filter(listaFiltros.get(2))
                                .peek(System.out::println)
                                .count();

                    } else if(cont == 4) {
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(listaFiltros.get(0))
                                .filter(listaFiltros.get(1))
                                .filter(listaFiltros.get(2))
                                .filter(listaFiltros.get(3))
                                .peek(System.out::println)
                                .count();
                    } else if (cont == 5){
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(arrayCondiciones[0])
                                .filter(arrayCondiciones[1])
                                .filter(arrayCondiciones[2])
                                .filter(arrayCondiciones[3])
                                .filter(arrayCondiciones[4])
                                .peek(System.out::println)
                                .count();
                    }


                    ///avisa que no hay coincidencias
                    if(coincidencias == 0){
                        System.out.println("No hay automoviles que coincidan con los filtros");
                    }


                }else{
                    ///muestra la lista resultante sin filtros
                    if(this.automovilRepository.getAutomovilList().size() == 0){
                        System.out.println("No hay automoviles");
                    }else{
                        this.automovilRepository.getAutomovilList().forEach(System.out::println);
                    }
                }
            }

        }while(opc != 0);
    }

    public void menuAutomovilAdmin(){
        int eleccion;
        while (true){
            automovilView.printMenuAutomovilAdmin();
            eleccion = Consola.ingresarXInteger("eleccion");
            switch (eleccion){
                case 0: //salir
                    return;
                case 1: //agregar
                    this.agregarAutomovilAlStock();
                    break;
                case 2://remover
                    this.borrarAutomovilEnStock();
                    break;
                case 3://modificar
                    this.modificar();
                    break;
                case 4://mostrar
                    Automovil find = find(Consola.ingresarXInteger("id del automovil"));
                    if (find == null){Consola.soutString("No se ha encontrado un automovil con el id ingresado.");}
                    else Consola.soutString(find.toString());
                    break;
                case 5://ver stock
                    this.mostrarAutomovilesEnStock();
                    break;
                case 6://ver lista filtrada
                    this.buscarAutomovilesXFiltro();
                    break;
                default: //Opción no reconocida
                    Consola.soutString("El dato ingresado no es válido. Reintentar");
                    break;
            }
        }
    }
    public void menuAutomovilVendedor(){
        int eleccion;
        while (true){
            automovilView.printMenuAutomovilVendedor();
            eleccion = Consola.ingresarXInteger("eleccion");
            switch (eleccion){
                case 0: //salir
                    return;
                case 1://mostrar
                    Automovil find = find(Consola.ingresarXInteger("id del automovil"));
                    if (find == null){}
                    else Consola.soutString(find.toString());
                    break;
                case 2://ver stock
                    this.mostrarAutomovilesEnStock();
                    break;
                case 3://ver lista filtrada
                    this.buscarAutomovilesXFiltro();
                    break;
                default: //Opción no reconocida
                    Consola.soutString("El dato ingresado no es válido. Reintentar");
                    break;
            }
        }
    }
}