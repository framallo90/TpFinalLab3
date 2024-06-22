package com.framallo90.Comprador.Model.Repository;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Model.Entity.Empleados;
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

public class CompradorRepository implements IRepository<Comprador,Integer> {
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
            }else{
                Integer mayor = listaCompradores.stream().mapToInt(c->c.getId()).max().getAsInt();
                Comprador.setCont(mayor);
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
    public void remove(Integer id) {
        Comprador remove = find(id);
        if (remove != null) {
            listaCompradores.remove(remove);
            updateFile();
        }
    }

    @Override
    public void update(Integer id) throws InvalidIdNotFound {
        Comprador comprador = find(id);
        if (comprador != null)
        {
            /// terminar
        }else
            throw new InvalidIdNotFound("El id ingresado no existe.");
    }

    @Override
    public Comprador find(Integer id) {
        if (this.listaCompradores.isEmpty()) {
            Consola.soutString("Aún no hay clientes registrados.");
            return null;
        }
        Optional<Comprador> devol = this.listaCompradores.stream().filter(c ->c.getId().equals(id)).findFirst();
        if(devol.isEmpty()){
            System.out.println("El comprador con id:"+id+", no existe, intentelo nuevamente.");
            return null;
        }else{
            return  devol.get();
        }
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

    public Set<Comprador> getListaCompradores() {
        return listaCompradores;
    }

}
