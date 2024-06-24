package com.framallo90.Automovil.Model.Repository;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.Interfaces.IRepository;
import com.framallo90.consola.Consola;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Repositorio para la gestión de objetos Automovil, implementando la interfaz IRepository.
 */
public class AutomovilRepository implements IRepository<Automovil, Integer> {
    private List<Automovil> automovilList;
    private Gson gson = new Gson();
    private static final String PATH = "src/main/resources/stockAutomoviles.json";
    /**
     * Constructor que inicializa el repositorio de Automovil cargando los datos desde el archivo JSON.
     */
    public AutomovilRepository() {
        loadAutomoviles();
    }
    /**
     * Añade un automóvil al repositorio y actualiza el archivo JSON.
     * @param object Automóvil a añadir.
     */
    @Override
    public void add(Automovil object) {
        this.automovilList.add(object);
        updateFile();
    }
    /**
     * Elimina un automóvil del repositorio por su ID y actualiza el archivo JSON.
     * @param id ID del automóvil a eliminar.
     * @throws InvalidIdNotFound Si el ID del automóvil no existe en la lista.
     */
    @Override
    public void remove(Integer id) throws InvalidIdNotFound {
        Automovil auto = find(id);
        if (auto != null) {
            this.automovilList.remove(auto);
            updateFile();
        } else {
            throw new InvalidIdNotFound("El Automovil con ID: "+ id +", NO se encuentra.");
        }
    }
    /**
     * Actualiza los atributos de un automóvil en el repositorio por su ID y actualiza el archivo JSON.
     * @param id ID del automóvil a actualizar.
     * @throws InvalidIdNotFound Si el ID del automóvil no existe en la lista.
     */
    @Override
    public void update(Integer id,Automovil automovil) throws InvalidIdNotFound {

        if (automovil != null) {

            updateFile();
        } else {
            throw new InvalidIdNotFound("El ID ingresado NO se encuentra registrado.");
        }
    }
    /**
     * Busca un automóvil por su ID en el repositorio.
     * @param integer ID del automóvil a buscar.
     * @return El automóvil encontrado o null si no existe.
     */
    @Override
    public Automovil find(Integer integer) {
        Optional<Automovil> devol = this.automovilList.stream().filter(a -> a.getId().equals(integer)).findFirst();
        return devol.orElse(null);
    }
    /**
     * Carga los automóviles desde el archivo JSON al iniciar el repositorio.
     */
    public void loadAutomoviles() {
        try (Reader reader = new FileReader(PATH)) {
            Type listType = new TypeToken<ArrayList<Automovil>>() {}.getType();
            automovilList = gson.fromJson(reader, listType);
            if (automovilList == null) {
                automovilList = new ArrayList<>();
            } else {
                if (!automovilList.isEmpty()) {
                    int id = 0;
                    for (Automovil automovil : this.automovilList)
                        if (id < automovil.getId()) id = automovil.getId();
                    Automovil.setCont(id);
                } else {
                    Automovil.setCont(0);
                }
            }
        } catch (FileNotFoundException e) {
            Consola.soutAlertString(e.getMessage());
        } catch (IOException io) {
            Consola.soutAlertString(io.getMessage());
        }
    }
    /**
     * Actualiza el archivo JSON con los cambios realizados en el repositorio.
     */
    public void updateFile() {
        try (Writer writer = new FileWriter(PATH)) {
            gson.toJson(automovilList, writer);
        } catch (IOException io) {
            Consola.soutAlertString(io.getMessage());
        }
    }
    /**
     * Obtiene la lista de automóviles actualmente en el repositorio.
     * @return Lista de automóviles.
     */
    public List<Automovil> getAutomovilList() {
        return automovilList;
    }
    /**
     * Verifica si el repositorio de automóviles está vacío.
     * @return true si la lista de automóviles está vacía, false si contiene elementos.
     */
    public boolean isEmpty() {
        return this.automovilList.isEmpty();
    }
}