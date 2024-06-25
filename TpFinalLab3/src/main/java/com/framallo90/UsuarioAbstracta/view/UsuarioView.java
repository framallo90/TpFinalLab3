package com.framallo90.UsuarioAbstracta.view;

import com.framallo90.consola.Consola;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase de utilidad que proporciona métodos estáticos para la validación y manipulación de correos electrónicos.
 * Permite verificar la validez de una dirección de correo electrónico utilizando expresiones regulares.
 *
 * Ejemplo de uso:
 * ```
 * String email = "usuario@example.com";
 * boolean valido = UsuarioView.isValidEmail(email);
 * if (valido) {
 *     System.out.println("El correo electrónico es válido.");
 * } else {
 *     System.out.println("El correo electrónico no es válido.");
 * }
 * ```
 *
 * @author Framallo
 * @version 1.0
 */
public class UsuarioView {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    /**
     * Verifica si una dirección de correo electrónico es válida utilizando una expresión regular.
     *
     * @param emailAddress La dirección de correo electrónico a validar.
     * @return true si la dirección de correo electrónico es válida, false si no lo es.
     */
    public static boolean isValidEmail(String emailAddress) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }
}
