package com.framallo90.Automovil.Controller;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;

import java.util.List;
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