package com.framallo90.Comprador.Model.Entity;

import com.framallo90.UsuarioAbstracta.Usuario;

public class Comprador extends Usuario
{
    private Integer id;
    private static Integer cont = 0;
    private String email;

    public Comprador(String nombre, String apellido, Integer dni, String email) {
        super(nombre, apellido, dni);
        this.id = ++cont;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Comprador{" + super.toString() +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}