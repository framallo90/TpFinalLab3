package com.framallo90.Comprador.Model.Repository;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.Interfaces.IRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class CompradorRepository implements IRepository<Comprador,String> {
    private static final String FILE_PATH = "src/main/resources/Compradores.json";
    private Gson gson = new Gson();
    private Set<Comprador> listaCompradores = new HashSet<>();

    public CompradorRepository() {
        loadFile();
    }

    public void loadFile(){
        try (Reader reader = new FileReader(FILE_PATH)){
            Type setType = new TypeToken<Set<Comprador>>(){}.getType();
            listaCompradores = gson.fromJson(reader, setType);
            if(listaCompradores == null){
                listaCompradores = new HashSet<>();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateFile(){
        try(Writer writer = new FileWriter(FILE_PATH)){
            gson.toJson(listaCompradores,writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void add(Comprador object) {
        listaCompradores.add(object);
        updateFile();
    }

    @Override
    public void remove(String id) throws InvalidIdNotFound{
        Comprador remove = find(id);
        if (remove != null) {
            listaCompradores.remove(remove);
            updateFile();
        }else
            throw new InvalidIdNotFound("El id ingresado no existe.");
    }

    @Override
    public void update(String id) throws InvalidIdNotFound {
        Comprador comprador = find(id);
        if (comprador != null)
        {
            /// terminar
        }else
            throw new InvalidIdNotFound("El id ingresado no existe.");
    }

    @Override
    public Comprador find(String id) {
        Comprador buscado = null;
        for (Comprador comprador : listaCompradores){
            if(comprador.getId().equals(Integer.parseInt(id)));{
                buscado = comprador;
            }
        }
        return buscado;
    }

    public void cambioNombre(Comprador comprador, String nuevoNom){
        comprador.setNombre(nuevoNom);
        updateFile();
    }

    public void cambioApellido(Comprador comprador, String nuevoApellido){
        comprador.setApellido(nuevoApellido);
        updateFile();
    }

    public void cambioDni(Comprador comprador, Integer dni){
        comprador.setDni(dni);
        updateFile();
    }

    public void cambioEmail(Comprador comprador, String nuevoEmail){
        comprador.setEmail(nuevoEmail);
        updateFile();
    }

}
