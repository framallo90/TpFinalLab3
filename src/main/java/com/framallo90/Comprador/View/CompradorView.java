package com.framallo90.Comprador.View;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.consola.Consola;

import java.util.Set;

/**
 * La clase {@code CompradorView} proporciona métodos para interactuar con el usuario y mostrar información sobre los compradores.
 */
public class CompradorView {

    /**
     * Solicita al usuario que ingrese un correo electrónico y lo valida usando una expresión regular.
     * Continúa solicitando la entrada hasta que se ingrese un correo electrónico válido.
     *
     * @return el correo electrónico ingresado y validado
     */

    public String ingresoEmail() {
        while (true) {
            String email = Consola.ingresarXStringSimple("su Correo Electrónico");

            // Patrón de expresión regular para validar email
            String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";

            // Validación con expresiones regulares
            if (email.matches(regex)) {
                return email;
            } else {
                System.out.println("Correo Electrónico Invalido. Intente nuevamente.");
            }
        }
    }

    /**
     * Muestra la información de un comprador en la consola.
     *
     * @param comprador el comprador cuya información se va a mostrar
     */
    public void muestroUnComprador(Comprador comprador) {
        System.out.println("----------");
        System.out.println("- ID.........: " + comprador.getId());
        System.out.println("- Nombre.....: " + comprador.getApellido() + ", " + comprador.getNombre());
        System.out.println("- DNI........: " + comprador.getDni());
        System.out.println("- E-Mail.....: " + comprador.getEmail());
        System.out.println("----------");
    }
    /**
     * Muestra la información de un conjunto de compradores en la consola.
     *
     * @param compradores el conjunto de compradores cuya información se va a mostrar
     */
    public void muestroCompradores(Set<Comprador> compradores) {
        for (Comprador comprador : compradores) {
            muestroUnComprador(comprador);
        }
    }

}