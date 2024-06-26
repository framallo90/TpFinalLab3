package com.framallo90.Venta.Model.Repository;

import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.Interfaces.IRepository;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.Venta.Model.Entity.Venta;
import com.framallo90.consola.Consola;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/*
*
* Ingresar dni:
El dni NO es válido, reintentar.
45567123
* */
/**
 * Repositorio que gestiona la persistencia y manipulación de las ventas de automóviles.
 * Implementa la interfaz IRepository para operaciones CRUD específicas de Venta.
 */
public class VentaRepository implements IRepository<Venta, Integer> {
    private Map<Integer, Venta> map; // Mapa que almacena las ventas, utilizando el ID de la venta como clave
    private static final String PATH_VENTAS = "src/main/resources/ventas.json"; // Ruta del archivo JSON para almacenar las ventas
    private final Gson gson = new Gson(); // Instancia de Gson para serialización/deserialización JSON
    
    /**
     * Constructor de la clase VentaRepository.
     * Carga las ventas almacenadas desde el archivo JSON al inicializar.
     */
    public VentaRepository() {
        this.loadVentas();
    }

    public void restoVenta(Venta venta){
        Integer autosV = venta.getEmpleados().getAutosvendidos();
        venta.getEmpleados().setAutosvendidos(autosV-1);
        saveVentas();
    }

    /**
     * Carga las ventas almacenadas desde el archivo JSON.
     * Si el archivo no existe, inicializa un nuevo mapa vacío.
     */
    public void loadVentas() {
        try (FileReader fileReader = new FileReader(PATH_VENTAS)) {
            Type listType = new TypeToken<Map<Integer, Venta>>() {}.getType();
            this.map = gson.fromJson(fileReader, listType);
            if (!map.isEmpty()){
                Venta.setCont(map.keySet().stream().max((a,b)-> a.compareTo(b)).get());
            }
        } catch (FileNotFoundException e) {
            // File not found, initialize empty map
            this.map = new HashMap<>();
        } catch (IOException e) {
            // Other IO exceptions, log or handle appropriately
            e.printStackTrace(); // Or use a logging library here
        } finally {
            // Ensure map is always initialized, even on errors
            if (this.map == null) {
                this.map = new HashMap<>();
            }
        }
    }

    /**
     * Guarda las ventas actuales en el archivo JSON.
     */
    public void saveVentas() {
        try (Writer fileWriter = new FileWriter(PATH_VENTAS)) {
            gson.toJson(this.map, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el mapa de ventas completo.
     *
     * @return El mapa que contiene todas las ventas almacenadas.
     */
    public Map<Integer, Venta> getMap() {
        return map;
    }

    /**
     * Agrega una nueva venta al repositorio.
     *
     * @param object La venta a ser agregada.
     */
    @Override
    public void add(Venta object) {
        if(map.containsKey(object.getIdVenta())){
            Consola.soutAlertString("IdExistente");
        }else{
            this.map.put(object.getIdVenta(), object);
            this.saveVentas();
        }

    }

    /**
     * Elimina una venta del repositorio por su ID.
     *
     * @param id El ID de la venta a ser eliminada.
     * @throws InvalidIdNotFound Si no se encuentra ninguna venta con el ID especificado.
     */
    @Override
    public void remove(Integer id) throws InvalidIdNotFound {
        if(this.map.containsKey(id)){
            this.map.remove(id);
            this.saveVentas();
        }else{
            throw new InvalidIdNotFound("No se ha encontrado una venta con id " + id + ".");
        }

    }

    @Override
    public void update(Integer id, Venta object) throws InvalidIdNotFound {
        Venta update = this.find(id);
        if (update != null) {
            map.replace(id,object);
            this.saveVentas();
        } else {
            throw new InvalidIdNotFound("NO se encontro la  Venta con ID: " + id + ".");
        }
    }
    /**
     * Actualiza una venta en el repositorio (pendiente de implementación).
     *
     * @param id El ID de la venta a ser actualizada.
     * @throws InvalidIdNotFound Si no se encuentra ninguna venta con el ID especificado.
     */


    /**
     * Busca una venta en el repositorio por su ID.
     *
     * @param id El ID de la venta a buscar.
     * @return La venta encontrada, o null si no se encuentra ninguna venta con ese ID.
     */
    @Override
    public Venta find(Integer id) throws InvalidIdNotFound {
        Optional<Venta> devol = this.map.values().stream().filter(c -> c.getIdVenta().equals(id)).findFirst();
        if (devol.isEmpty()) {
            throw new InvalidIdNotFound("No se ha encontrado una venta con ese ID");

        } else {
            return devol.get();
        }
    }
    /**
     * Actualiza el empleado de una venta específica.
     *
     * @param idVenta El ID de la venta a actualizar.
     * @param nuevo   El nuevo empleado asignado a la venta.
     */
    public void cambioEmpleados(Integer idVenta, Empleados nuevo) {
        try{
            Venta buscar = this.find(idVenta);
            buscar.setEmpleados(nuevo);
            this.saveVentas();
        }catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }

    }
    /**
     * Actualiza el comprador de una venta específica.
     *
     * @param idVenta El ID de la venta a actualizar.
     * @param comp  El nuevo comprador asignado a la venta.
     */
    public void cambioComprador(Integer idVenta, Comprador comp) {

        try{
            Venta nuevo = this.find(idVenta);
            nuevo.setComprador(comp);
            this.saveVentas();
        }catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }

    /**
     * Actualiza el método de pago de una venta específica.
     *
     * @param idVenta El ID de la venta a actualizar.
     * @param nuevo   El nuevo método de pago asignado a la venta.
     */
    public void cambioMetodoDePago(Integer idVenta, MetodoDePago nuevo) {

        try{
            Venta buscar = this.find(idVenta);
            buscar.setMetodoDePago(nuevo);
            this.saveVentas();
        }catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }

    /**
     * Verifica si el repositorio de ventas está vacío.
     *
     * @return true si el repositorio no contiene ventas; false de lo contrario.
     */
    public boolean isEmpty() {
        return this.map.isEmpty();
    }
}