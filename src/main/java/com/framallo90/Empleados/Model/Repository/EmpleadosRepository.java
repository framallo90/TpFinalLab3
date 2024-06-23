/**
 * Repositorio de Empleados
 *
 * Esta clase implementa la interfaz `IRepository<Empleados, Integer>` para gestionar el acceso a los datos de los empleados del sistema.
 * Los datos se almacenan en un archivo JSON llamado "empleados.json".
 *
 * @see IRepository
 * @see Empleados
 */
package com.framallo90.Empleados.Model.Repository;

import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Interfaces.IRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class EmpleadosRepository implements IRepository<Empleados, Integer> {
    private Set<Empleados> empleados;
    private static final String PATH_EMPLEADOS = "src/main/resources/empleados.json";
    private final Gson gson = new Gson();

    /**
     * Constructor de la clase `EmpleadosRepository`.
     * Carga la empleadosa de empleados desde el archivo JSON y establece el contador de empleados.
     */
    public EmpleadosRepository() {
        this.loadEmpleados();
    }

    /**
     * Obtiene la empleadosa de empleados.
     *
     * @return La empleadosa de empleados.
     */
    public Set<Empleados> getEmpleados() {
        return empleados;
    }

    /**
     * Carga los empleados desde el archivo JSON.
     * Si el archivo no se encuentra, inicializa la empleadosa como vacía.
     */
    public void loadEmpleados() {
        try (FileReader fileReader = new FileReader(PATH_EMPLEADOS)) {
            Type type = new TypeToken<Set<Empleados>>(){}.getType();
            this.empleados = gson.fromJson(fileReader, type);
            if (this.empleados == null)
                this.empleados = new HashSet<>();
            else
                Empleados.setCont(this.empleados.stream().map(e->e.getId()).max((a,b) -> a.compareTo(b)).get());

        } catch (FileNotFoundException e) {
            this.empleados = new HashSet<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda los empleados en el archivo JSON.
     */
    public void saveEmpleados() {
        try (FileWriter fileWriter = new FileWriter(PATH_EMPLEADOS)) {
            gson.toJson(this.empleados, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Agrega un empleado a la empleadosa y guarda los cambios en el archivo JSON.
     *
     * @param object El empleado a agregar.
     */
    @Override
    public void add(Empleados object) {
        this.empleados.add(object);
        this.saveEmpleados();
    }

    /**

     * Elimina un empleado de la empleadosa por su ID y guarda los cambios en el archivo JSON.
     *
     * @param id El ID del empleado a eliminar.
     */
    @Override
    public void remove(Integer id) {
        Empleados remover = this.find(id);
        if (remover == null) return;
        this.empleados.remove(remover);
        this.saveEmpleados();

    }

    /**
     * Actualiza un empleado existente en la empleadosa.
     *
     * **Nota:** Este método no está implementado actualmente. Se necesita un mecanismo para identificar el empleado a actualizar y proporcionar los nuevos datos.
     *
     * @param id El ID del empleado a actualizar.
     */
    @Override
    public void update(Integer id) {
        // TODO: Implementar la funcionalidad de actualización de un empleado.
        throw new UnsupportedOperationException("La actualización de empleados aún no está implementada");
    }

    /**
     * Busca un empleado por su ID.
     *
     * @param id El ID del empleado a buscar.
     * @return El objeto Empleado encontrado o null si no se encuentra.
     */
    @Override
    public Empleados find(Integer id) {
        Optional<Empleados> devol = this.empleados.stream().filter(e -> e.getId().equals(id)).findFirst();
        return devol.orElse(null);
    }

    /**
     * Actualiza la cantidad de autos vendidos de un empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param autosVendidos La nueva cantidad de autos vendidos.
     */
    public void cambioAutosVendidos(Empleados empleados, Integer autosVendidos) {
        empleados.setAutosvendidos(autosVendidos);
        this.saveEmpleados();
    }

    /**
     * Actualiza el nombre de un empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevoNombre El nuevo nombre del empleado.
     */
    public void cambioNombre(Empleados empleados, String nuevoNombre) {
        empleados.setNombre(nuevoNombre);
        this.saveEmpleados();
    }

    /**
     * Actualiza el apellido de un empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevoApellido El nuevo apellido del empleado.
     */
    public void cambioApellido(Empleados empleados, String nuevoApellido) {
        empleados.setApellido(nuevoApellido);
        this.saveEmpleados();
    }

    /**
     * Actualiza el DNI de un empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevoDni El nuevo DNI del empleado.
     * @throws IllegalArgumentException Si el nuevo DNI ya pertenece a otro empleado.
     */
    public void cambioDni(Empleados empleados, Integer nuevoDni) {
        for (Empleados value : this.empleados) {
            if (value.getDni().equals(nuevoDni))
                throw new IllegalArgumentException("El DNI ya pertenece a otro empleado");
        }
        empleados.setDni(nuevoDni);
        this.saveEmpleados();
    }

    /**
     * Actualiza el nombre de usuario de un empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevoUsername El nuevo nombre de usuario del empleado.
     */
    public void cambioUsername(Empleados empleados, String nuevoUsername) {
        empleados.setUsername(nuevoUsername);
        this.saveEmpleados();
    }

    /**
     * Actualiza la contraseña de un empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevaPassword La nueva contraseña del empleado.
     * **Se recomienda almacenar las contraseñas de forma segura, como utilizando un algoritmo de hash.**
     */
    public void cambioPassword(Empleados empleados, String nuevaPassword) {
        empleados.setPassword(nuevaPassword);
        this.saveEmpleados();
    }

    /**
     * Actualiza el tipo de empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevoTipo El nuevo tipo de empleado.
     */
    public void cambioTipo(Empleados empleados, String nuevoTipo) {
        empleados.setTipo(nuevoTipo);
        this.saveEmpleados();
    }
}
