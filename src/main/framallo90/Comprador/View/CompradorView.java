package com.framallo90.Comprador.View;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.consola.Consola;

import java.util.Set;

public class CompradorView {
    public String ingresoEmail() {
        while (true) {
            String email = Consola.ingresarXStringSimple("Ingrese su correo electrónico: ");

            // Patrón de expresión regular para validar email
            String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";

            // Validación con expresiones regulares
            if (email.matches(regex)) {
                return email;
            } else {
                System.out.println("Correo electrónico no válido. Intente nuevamente.");
            }
        }
    }

    public void muestroUnComprador(Comprador comprador){
        System.out.println("=========================================");
        System.out.println("ID: " + comprador.getId());
        System.out.println("Nombre: " + comprador.getApellido() + ", " + comprador.getNombre());
        System.out.println("Dni: "+comprador.getDni());
        System.out.println("E-Mail: "+comprador.getEmail());
        System.out.println("=========================================");
    }

    public void muestroCompradores(Set<Comprador> compradores){
        for (Comprador comprador : compradores){
            muestroUnComprador(comprador);
        }
    }
}
