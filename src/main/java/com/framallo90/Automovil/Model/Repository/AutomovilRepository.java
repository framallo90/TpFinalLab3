package com.framallo90.Automovil.Model.Repository;

import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.Interfaces.IRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AutomovilRepository implements IRepository<Automovil,Integer> {
    private List<Automovil> automovilList;
    private Gson gson = new Gson();
    private static final String PATH = "src/main/resources/stockAutomoviles.json";

    public AutomovilRepository()
    {
        loadAutomoviles();
    }

    @Override
    public void add(Automovil object) {
        this.automovilList.add(object);
        updateFile();
    }

    @Override
    public void remove(Integer id) throws InvalidIdNotFound {
        Automovil hola = find(id);
        if (hola != null) {
            this.automovilList.remove(hola);
            updateFile();
        }
        else
            throw new InvalidIdNotFound("El id ingresado no existe.");
    }

    @Override
    public void update(Integer id) throws InvalidIdNotFound{ /// -> Terminar
        /// terminar
        Automovil automovil = find(id);
        if (automovil != null)
        {
            /// terminar
        }else
            throw new InvalidIdNotFound("El id ingresado no existe.");
    }

    @Override
    public Automovil find(Integer integer) {
        for (Automovil automovil : automovilList)
        {
            if (automovil.getId().equals(integer))
                return automovil;
        }
        return null;
    }

    public void loadAutomoviles()
    {
        try(Reader reader = new FileReader(PATH)) {
            Type listType = new TypeToken<ArrayList<Automovil>>(){}.getType();
            automovilList = gson.fromJson(reader,listType);
            if(automovilList == null)
            {
                automovilList = new ArrayList<>();
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
}
