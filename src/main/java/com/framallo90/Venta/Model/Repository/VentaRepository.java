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
public class VentaRepository  implements IRepository<Venta,Integer> {
    private Map<Integer, Venta> map;
    private static final String PATH_VENTAS = "src/main/resources/ventas.json";
    private final Gson gson = new Gson();
    public VentaRepository() {
        this.loadVentas();
    }
    public void loadVentas() {
        try (FileReader fileReader = new FileReader(PATH_VENTAS)) {
            Type listType = new TypeToken<Map<Integer, Venta>>() {}.getType();
            this.map = gson.fromJson(fileReader, listType);
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
    public void saveVentas() {
        try (Writer fileWriter = new FileWriter(PATH_VENTAS)) {
            gson.toJson(this.map, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<Integer, Venta> getMap() {
        return map;
    }
    @Override
    public void add(Venta object) {
        this.map.put(object.getIdVenta(),object);
        this.saveVentas();
    }
    @Override
    public void remove(Integer integer) throws Exception {
        Venta remove = this.map.remove(integer);
        if (remove == null )throw new InvalidIdNotFound("No se ha encontrado una venta con id " + integer + ".");
        else Consola.soutString("Venta removida correctamente.");
        this.saveVentas();
    }
    @Override
    public void update(Integer integer) throws Exception {
        Venta update = this.find(integer);
        if (update != null ){
            //terminar
        }
        else throw new InvalidIdNotFound("No se ha encontrado una venta de id " + integer + ".");
    }
    @Override
    public Venta find(Integer id) {
        Optional<Venta> devol = this.map.values().stream().filter(c ->c.getIdVenta().equals(id)).findFirst();
        if(devol.isEmpty()){
            System.out.println("El comprador con id:"+id+", no existe, intentelo nuevamente.");
            return null;
        }else{
            return  devol.get();
        }
    }
    public void cambioEmpleados(Integer idVenta, Empleados nuevo){
        Venta buscar = this.find(idVenta);
        if (buscar != null) {
            buscar.setEmpleados(nuevo);
            this.saveVentas();
        }
    }
    public void cambioComprador(Integer idVenta, Comprador nuevo){
        Venta buscar = this.find(idVenta);
        if (buscar != null) {
            buscar.setComprador(nuevo);
            this.saveVentas();
        }
    }
    public void cambioAutomovil(Integer idVenta, Automovil nuevo){
        Venta buscar = this.find(idVenta);
        if (buscar != null )
            buscar.setAutomovil(nuevo);
        this.saveVentas();
    }
    public void cambioMetodoDePago(Integer idVenta, MetodoDePago nuevo){
        Venta buscar = this.find(idVenta);
        if (buscar != null )
            buscar.setMetodoDePago(nuevo);
        this.saveVentas();
    }

    public boolean isEmpty(){
        return this.map.isEmpty();
    }
}