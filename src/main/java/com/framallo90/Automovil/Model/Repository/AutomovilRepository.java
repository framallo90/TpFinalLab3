package com.framallo90.Automovil.Model.Repository;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Empleados.Model.Entity.Empleados;
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

import static com.framallo90.Automovil.Model.Entity.Automovil.getCont;

public class AutomovilRepository implements IRepository<Automovil,Integer> {
    private List<Automovil> automovilList;
    private Gson gson = new Gson();
    private static final String PATH = "src/main/resources/stockAutomoviles.json";

    public AutomovilRepository()
    {
        loadAutomoviles(); /// Carga / creacion de lista
    }

    @Override
    public void add(Automovil object) {
        this.automovilList.add(object);
        updateFile(); /// actualizamos el archivo
        /**
         * Metodo que agrega un automovil a la lista, el metodo se encuentra altamente protegido a parametros
         * erroneamente introducidos por el usuario
         */
    }

    @Override
    public void remove(Integer id) throws InvalidIdNotFound {
        Automovil auto = find(id);
        if (auto != null) {
            this.automovilList.remove(auto);
            Consola.soutString("El vehiculo de id: "+id+" se ha removido correctamente.");
            updateFile();
        }
        else
            throw new InvalidIdNotFound("El automovil de id: "+ id +" ingresado no existe.");
    }
    @Override
    public void update(Integer id) throws InvalidIdNotFound{
        Automovil automovil = find(id);
        if (automovil != null) /// Se ejecuta si lo encuentra en la lista
        {
            System.out.println("1. Marca\n2. Modelo\n3. Precio\n4. Patente \n5. Anio");
            Integer IDMod = Consola.ingresarXInteger("numero");
            switch (IDMod)
            {
                case 1:
                    automovil.setMarca(Consola.ingresarXString("marca"));
                    break;
                case 2:
                    automovil.setModelo(Consola.ingresarXStringSimple("modelo"));
                    break;
                case 3:
                    automovil.setPrecio(Consola.ingresarXdouble("precio"));
                    break;
                case 4:
                    automovil.setPatente(Consola.patente("patente"));
                    break;
                case 5:
                    automovil.setAnio(Consola.ingresarXInteger("anio"));
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
                /**
                 * Para evitar cargar todo el vehiculo desde 0, utilizamos un switch
                 * que, según lo que quiera el usuario, diferenciara entre los atributos que contiene
                 * el vehiculo, logrando así una mayor modularización y flexibilidad en su uso.
                 */
            }
            updateFile(); /// actualizamos el archivo
        }else
            throw new InvalidIdNotFound("El id ingresado no existe.");
    }

    @Override
    public Automovil find(Integer integer) {
        /**
         * Método que se encarga de buscar un automovil por ID en la lista, si no lo encuentra
         * o la lista esta vacía devuelve null
         */
        Optional<Automovil> devol = this.automovilList.stream().filter(a ->a.getId().equals(integer)).findFirst();
        if(devol.isEmpty()){
            return null;
        }else{
            return  devol.get();
        }
    }

    public void loadAutomoviles()
    {
        /**
         * Método principal del repositorio, procede con la deserialization del archivo para confirmar si
         * está cargado o no,
         */
        try(Reader reader = new FileReader(PATH)) {
            Type listType = new TypeToken<ArrayList<Automovil>>(){}.getType();
            automovilList = gson.fromJson(reader,listType);
            if(automovilList == null)
            {
                automovilList = new ArrayList<>();
            }
            else {
                if (!automovilList.isEmpty())
                {
                    int id = 0;
                    for (Automovil automovil : this.automovilList)
                        if (id<automovil.getId()) id = automovil.getId();
                    Automovil.setCont(id); // size-1
                }else
                    Automovil.setCont(0);

                /**
                * Seteo del contador estático, importante para no perder su
                 * referencia a la hora del reinicio del programa
                 * 1er caso. El archivo esta completamente vacio. Se creara una
                 * nueva arraylist
                 * 2do caso. La lista tiene elementos, cargamos el contador estático
                 * para no perder su referencia
                 * 3er caso. La lista fue vaciada por el usuario, el JSON quedará cargado
                 * con una lista vacia '[]', por lo que tenemos que evaluar esta situacion
                 * para evitar que el programa se rompa, solamente setearemos el contador en 0.
                 */
            }
            /// Manejar el counter statico

        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
    public void updateFile()
    {
        /**
         * Método serializador del archivo, escribe los cambios realizados en la lista local.
         */
        try(Writer writer = new FileWriter(PATH)) {
            gson.toJson(automovilList,writer);
        }catch (IOException io)
        {
            System.out.println(io.getMessage());
        }
    }
    public List<Automovil> getAutomovilList() {
        return automovilList;
    }

    public boolean isEmpty(){
        return this.automovilList.isEmpty();
    }
}
