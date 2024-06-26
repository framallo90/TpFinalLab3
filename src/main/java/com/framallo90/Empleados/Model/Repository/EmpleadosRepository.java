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
import com.framallo90.Excepciones.CeroAdminsException;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.Interfaces.IRepository;
import com.framallo90.consola.Consola;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadosRepository implements IRepository<Empleados, Integer> {
    private List<Empleados> list;
    private static final String PATH_EMPLEADOS = "src/main/resources/empleados.json";
    private final Gson gson = new Gson();

    /**
     * Constructor de la clase `EmpleadosRepository`.
     * Carga la lista de empleados desde el archivo JSON y establece el contador de empleados.
     */
    public EmpleadosRepository() {
        this.loadEmpleados();
        if (this.list.isEmpty()) {
            Empleados admin = new Empleados("A", "A", 0, 0, "A", "A", "administrador");
            admin.setId(0);
            this.list.add(admin);
            this.saveEmpleados();
            Empleados.setCont(admin.getId());
        } else {
            Empleados.setCont(this.list.get(this.list.size() - 1).getId());
        }
    }

    /**
     * Obtiene la lista de empleados.
     *
     * q@return La lista de empleados.
     */
    public List<Empleados> getList() {
        return list;
    }

    /**
     * Carga los empleados desde el archivo JSON.
     * Si el archivo no se encuentra, inicializa la lista como vacía.
     */
    public void loadEmpleados() {
        try (FileReader fileReader = new FileReader(PATH_EMPLEADOS)) {
            Type type = new TypeToken<List<Empleados>>(){}.getType();
            this.list = gson.fromJson(fileReader, type);
            if (this.list == null) {
                this.list = new ArrayList<>();
            }else{
                Empleados.setCont(this.list.stream().map(c->c.getId()).max((a,b)-> a.compareTo(b)).get());
            }
        } catch (FileNotFoundException e) {
            this.list = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda los empleados en el archivo JSON.
     */
    public void saveEmpleados() {
        try (FileWriter fileWriter = new FileWriter(PATH_EMPLEADOS)) {
            gson.toJson(this.list, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Agrega un empleado a la lista y guarda los cambios en el archivo JSON.
     *
     * @param object El empleado a agregar.
     */
    @Override
    public void add(Empleados object) {
        this.list.add(object);
        this.saveEmpleados();
    }


    @Override
    public void remove(Integer id) throws CeroAdminsException,InvalidIdNotFound {
        Empleados remover = this.find(id);
        if(remover.getTipo().equals("administrador") && contAdmins()==1){
            throw new CeroAdminsException();
        }
        this.list.remove(remover);
        this.saveEmpleados();
    }

    /**
     * Actualiza un empleado existente en la lista.
     *
     * **Nota:** Este método no está implementado actualmente. Se necesita un mecanismo para identificar el empleado a actualizar y proporcionar los nuevos datos.
     *
     * @param id El ID del empleado a actualizar.
     */
    @Override
    public void update(Integer id,Empleados empleados) throws InvalidIdNotFound {
        int i;
        for(i = 0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                list.set(i,empleados);
                saveEmpleados();
                break;
            }
        }
        if(i == list.size()){
            throw new InvalidIdNotFound();
        }
    }

    /**
     * Busca un empleado por su ID.
     *
     * @param id El ID del empleado a buscar.
     * @return El objeto Empleado encontrado o null si no se encuentra.
     */
    @Override
    public Empleados find(Integer id) throws InvalidIdNotFound{
        Optional<Empleados> devol = this.list.stream().filter(e -> e.getId().equals(id)).findFirst();
        if(devol.isEmpty()){
            throw new InvalidIdNotFound();
        }
        return devol.get();
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
        try {
            update(empleados.getId(),empleados);
            this.saveEmpleados();
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }

    }

    /**
     * Actualiza el apellido de un empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevoApellido El nuevo apellido del empleado.
     */
    public void cambioApellido(Empleados empleados, String nuevoApellido) {
        empleados.setApellido(nuevoApellido);
        try {
            update(empleados.getId(),empleados);
            this.saveEmpleados();
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }

    }

    /**
     * Actualiza el DNI de un empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevoDni El nuevo DNI del empleado.
     * @throws IllegalArgumentException Si el nuevo DNI ya pertenece a otro empleado.
     */
    public void cambioDni(Empleados empleados, Integer nuevoDni) throws IllegalArgumentException{

        empleados.setDni(nuevoDni);
        try {
            update(empleados.getId(),empleados);
            this.saveEmpleados();
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }


    }

    /**
     * Actualiza el nombre de usuario de un empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevoUsername El nuevo nombre de usuario del empleado.
     */
    public void cambioUsername(Empleados empleados, String nuevoUsername) {
        empleados.setUsername(nuevoUsername);
        try {
            update(empleados.getId(),empleados);
            this.saveEmpleados();
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
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
        try {
            update(empleados.getId(),empleados);
            this.saveEmpleados();
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }

    }

    /**
     * Actualiza el tipo de empleado.
     *
     * @param empleados El objeto Empleado a actualizar.
     * @param nuevoTipo El nuevo tipo de empleado.
     */
    public void cambioTipo(Empleados empleados, String nuevoTipo) {
        empleados.setTipo(nuevoTipo);
        try {
            update(empleados.getId(),empleados);
            this.saveEmpleados();
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }

    }

    /**
     * Cuenta la cantidad de empleados que tienen el tipo "administrador" o "admin".
     *
     * @return La cantidad de empleados que son administradores o admins.
     */
    public long contAdmins()  {
        return  list.stream()
                .filter(e -> "administrador".equalsIgnoreCase(e.getTipo()) || "admin".equalsIgnoreCase(e.getTipo()))
                .count();
    }

}
