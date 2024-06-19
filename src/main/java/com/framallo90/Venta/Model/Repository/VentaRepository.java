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
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class VentaRepository  implements IRepository<Venta,Integer> {
    private Map<Integer, Venta> map;
    private static final String PATH_VENTAS = "src/main/resources/ventas.json";
    private final Gson gson;

    public VentaRepository() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        this.loadVentas();
    }

    // Custom LocalDateAdapter class
    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            // You can customize the format here (e.g., "yyyy-MM-dd")
            out.value(value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            String dateString;
            try {
                // Check for null values first
                if (in.peek() == JsonToken.NULL) {
                    in.skipValue();
                    return null;
                }
                // Read the date string using the appropriate method
                dateString = in.nextString();

                // Define a custom DateTimeFormatter for the specific format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                // Parse the date string using the custom formatter
                return LocalDate.parse(dateString, formatter);
            } catch (Exception e) {
                // Handle potential exceptions during reading
                e.printStackTrace();
                return null;
            }
        }

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
    public Venta find(Integer integer) {
        return this.map.get(integer);
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

    public void cambioFecha(Integer idVenta, LocalDate nueva){
        Venta buscar = this.find(idVenta);
        if (buscar != null )
            buscar.setFecha(nueva);
        this.saveVentas();
    }
    public void cambioMetodoDePago(Integer idVenta, MetodoDePago nuevo){
        Venta buscar = this.find(idVenta);
        if (buscar != null )
            buscar.setMetodoDePago(nuevo);
        this.saveVentas();
    }

}