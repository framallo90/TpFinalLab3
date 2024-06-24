package com.framallo90.Comprador.Model.Repository;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.Interfaces.IRepository;
import com.framallo90.consola.Consola;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * La clase {@code CompradorRepository} implementa la interfaz {@code IRepository} y proporciona métodos
 * para gestionar una colección de objetos {@code Comprador}, incluyendo operaciones de CRUD y persistencia de datos en un archivo JSON.
 */
public class CompradorRepository implements IRepository<Comprador, Integer> {
    private static final String FILE_PATH = "src/main/resources/Compradores.json";
    private Gson gson = new Gson();
    private Set<Comprador> listaCompradores = new HashSet<>();

    /**
     * Crea una nueva instancia de {@code CompradorRepository} y carga los datos desde el archivo JSON.
     */
    public CompradorRepository() {
        loadFile();
    }

    /**
     * Carga los datos de compradores desde el archivo JSON.
     * Si el archivo está vacío, inicializa una nueva colección de compradores.
     * Si hay datos, establece el contador de identificadores al valor máximo encontrado.
     */
    public void loadFile() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type setType = new TypeToken<Set<Comprador>>() {}.getType();
            listaCompradores = gson.fromJson(reader, setType);
            if (listaCompradores == null) {
                listaCompradores = new HashSet<>();
            } else {
                Integer mayor = listaCompradores.stream().mapToInt(Comprador::getId).max().getAsInt();
                Comprador.setCont(mayor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza el archivo JSON con los datos actuales de la colección de compradores.
     */
    public void updateFile() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(listaCompradores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Añade un nuevo comprador a la colección y actualiza el archivo JSON.
     *
     * @param object el comprador a añadir
     */
    @Override
    public void add(Comprador object) {
        listaCompradores.add(object);
        updateFile();
    }

    /**
     * Elimina un comprador de la colección por su identificador y actualiza el archivo JSON.
     *
     * @param id el identificador del comprador a eliminar
     */
    @Override
    public void remove(Integer id) throws InvalidIdNotFound{
        Comprador remove = find(id);
        if (remove != null) {
            listaCompradores.remove(remove);
            updateFile();
        }else{
            throw new InvalidIdNotFound("EL ID NO se encuentra registrado!.");
        }
    }

    /**
     * Actualiza la información de un comprador por su identificador.
     * (Método incompleto, necesita implementación)
     *
     * @param id el identificador del comprador a actualizar
     * @throws InvalidIdNotFound si el comprador con el id especificado no existe
     */
    @Override
    public void update(Integer id,Comprador comprador) throws InvalidIdNotFound {

        if (comprador != null) {
            // Método incompleto, necesita implementación
        } else {
            throw new InvalidIdNotFound("EL ID NO se encuentra registrado!.");
        }
    }

    /**
     * Busca un comprador en la colección por su identificador.
     *
     * @param id el identificador del comprador a buscar
     * @return el comprador encontrado o {@code null} si no existe
     */
    @Override
    public Comprador find(Integer id) throws InvalidIdNotFound{
        if (this.listaCompradores.isEmpty()) {
            Consola.soutString("Aún NO hay clientes registrados.");
            return null;
        }
        Optional<Comprador> devol = this.listaCompradores.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (devol.isEmpty()) {
            throw new InvalidIdNotFound("El Comprador con ID: " + id + ", NO existe. Intentelo Nuevamente.");
        } else {
            return devol.get();
        }
    }

    /**
     * Cambia el nombre de un comprador y actualiza el archivo JSON.
     *
     * @param comprador el comprador cuyo nombre será cambiado
     * @param nuevoNom el nuevo nombre del comprador
     */
    public void cambioNombre(Comprador comprador, String nuevoNom) {
        comprador.setNombre(nuevoNom);
        updateFile();
    }

    /**
     * Cambia el apellido de un comprador y actualiza el archivo JSON.
     *
     * @param comprador el comprador cuyo apellido será cambiado
     * @param nuevoApellido el nuevo apellido del comprador
     */
    public void cambioApellido(Comprador comprador, String nuevoApellido) {
        comprador.setApellido(nuevoApellido);
        updateFile();
    }

    /**
     * Cambia el DNI de un comprador y actualiza el archivo JSON.
     *
     * @param comprador el comprador cuyo DNI será cambiado
     * @param dni el nuevo DNI del comprador
     */
    public void cambioDni(Comprador comprador, Integer dni) {
        comprador.setDni(dni);
        updateFile();
    }

    /**
     * Cambia el email de un comprador y actualiza el archivo JSON.
     *
     * @param comprador el comprador cuyo email será cambiado
     * @param nuevoEmail el nuevo email del comprador
     */
    public void cambioEmail(Comprador comprador, String nuevoEmail) {
        comprador.setEmail(nuevoEmail);
        updateFile();
    }

    /**
     * Obtiene la colección de todos los compradores.
     *
     * @return un conjunto de todos los compradores
     */
    public Set<Comprador> getListaCompradores() {
        return listaCompradores;
    }
}