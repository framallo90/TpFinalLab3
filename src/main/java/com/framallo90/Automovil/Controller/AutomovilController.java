package com.framallo90.Automovil.Controller;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
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
    public void modificarAutomovilEnStock()
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
    public void modificarAutomovilEnStock(Automovil automovil)
    {
        try {
            this.automovilRepository.update(automovil.getId());
        }catch (InvalidIdNotFound e)
        {
            System.out.println(e.getMessage());
        }
        /**
         * Manejamos el caso de que el ID no este en la lista
         */
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
        Stack<List<Automovil>> pilaListas = new Stack<>();
        Stack<String> pilaTags = new Stack<>();
        pilaListas.push(this.automovilRepository.getAutomovilList().stream().toList());
        int opc = -1;

        do{
            System.out.println("Menu \n1 - filtrar por marca\n2 - filtrar por modelo\n3 - filtrar por anio\n4 - establecer precio maximo\n5 - establecer precio minimo");
            if(pilaListas.size() > 1){
                System.out.println("6 - quitar filtro");
            }
            System.out.println("0 - atras");

            opc = Consola.ingresarXInteger("opcion");
            switch (opc){
                case 1:
                    String marca = automovilView.ingresoMarca();
                    pilaListas.push(pilaListas.getLast().stream().filter(a -> a.getMarca().equalsIgnoreCase(marca)).collect(Collectors.toList()));
                    pilaTags.push(" Marca: " + marca+" |");
                    break;

                case 2:
                    String modelo = automovilView.ingresoModelo();
                    pilaListas.push(pilaListas.getLast().stream().filter(a -> a.getModelo().equalsIgnoreCase(modelo)).collect(Collectors.toList()));
                    pilaTags.push(" Modelo: " + modelo+" |");
                    break;

                case 3:
                    Integer anio = automovilView.ingresoAnio();
                    pilaListas.push(pilaListas.getLast().stream().filter(a -> a.getAnio() == anio).collect(Collectors.toList()));
                    pilaTags.push(" Anio: " + anio.toString()+" |");
                    break;

                case 4:
                    Double max = Consola.ingresarXdouble("maximo");
                    pilaListas.push(pilaListas.getLast().stream().filter(a -> a.getPrecio() <= max).collect(Collectors.toList()));
                    pilaTags.push(" Precio maximo: " + max.toString()+" |");
                    break;
                case 5:
                    Double min = Consola.ingresarXdouble("minimo");
                    pilaListas.push(pilaListas.getLast().stream().filter(a -> a.getPrecio()>=min).collect(Collectors.toList()));
                    pilaTags.push(" Precio minimo: " + min.toString() +" |");
                    break;
                case 6:
                    if(pilaListas.size()>1){
                        pilaListas.pop();
                        pilaTags.pop();
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcion no reconocida");
                    break;
            }
            if(!pilaTags.isEmpty()){
                System.out.print("|Filtros| |");
                for(String st:pilaTags){
                    System.out.print(st);
                }
                System.out.println();
            }
            if(!pilaListas.isEmpty()){
                pilaListas.getLast().forEach(System.out::println);
            }else{
                System.out.println("No quedan automoviles que cumplan la condicion");
            }

        }while(opc != 0);
    }
}