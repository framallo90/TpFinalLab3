/**
 * Esta clase proporciona métodos estáticos para la interacción con el usuario a través de la consola.
 *
 * @author Framballo90
 * @since v1.0
 */
package com.framallo90.consola;
import java.util.Scanner;
public class Consola {
    /**
     * Objeto Scanner estático para la lectura de la entrada del usuario.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Imprime un mensaje en la consola (salida estándar).
     *
     * @param string El mensaje a imprimir.
     */
    public static void soutString(String string) {
        System.out.println(string);
    }

    /**
     * Limpia el buffer del Scanner si hay datos pendientes de lectura.
     *
     * Este método verifica si hay datos sin leer en el buffer del Scanner y, si es así, los consume para evitar problemas de lectura posterior.
     */
    public static void limpiarBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Consume la línea restante en el buffer
        }
    }

    /**
     * Solicita al usuario que ingrese un String y lo valida para que solo contenga letras y espacios.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return El String ingresado por el usuario.
     * @throws IllegalArgumentException Si el usuario ingresa un dato inválido (no solo letras y espacios).
     */
    public static String ingresarXString(String x) {
        while (true) {
            System.out.println("Ingresar " + x + ": ");
            String s = scanner.nextLine();
            if (s.matches("[a-zA-Z\\s]+")) {
                return s;
            } else {
                System.out.println("Ingresar un dato valido.");
            }
        }
    }

    /**
     * Solicita al usuario que ingrese un número entero y lo valida para que sea mayor o igual a cero.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return El número entero ingresado por el usuario.
     * @throws IllegalArgumentException Si el usuario ingresa un número negativo.
     */
    public static Integer ingresarXInteger(String x) {
        System.out.println("Ingresar " + x + ": ");
        while (!scanner.hasNextInt()) {
            System.out.println("El dato ingresado no es valido. Por favor, ingrese un número entero:");
            scanner.next(); // Limpiar la entrada no válida
        }
        Integer numero = scanner.nextInt();
        if (numero < 0) {
            throw new IllegalArgumentException("El numero debe ser mayor o igual a 0.");
        }
        return numero;
    }

    /**
     * Solicita al usuario que ingrese un número double.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return El número double ingresado por el usuario.
     */
    public static Double ingresarXdouble(String x) {
        System.out.println("Ingresar " + x + ": ");
        while (!scanner.hasNextDouble()) {
            System.out.println("El dato ingresado no es valido. Por favor, ingrese un double");
            scanner.next(); // Limpiar la entrada no válida
        }
        Double numero = scanner.nextDouble();
        scanner.nextLine(); /// buffer
        return numero;
    }

    /**
     * Solicita al usuario que ingrese una patente de vehículo en el formato AAA-123.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return La patente del vehículo ingresada por el usuario en mayúsculas.
     * @throws IllegalArgumentException Si el usuario ingresa una patente en un formato inválido.
     */
    public static String patente(String x) {
        StringBuilder patente = new StringBuilder();
        while (true) {
            System.out.println("Ingresar " + x + ": ");
            String s = scanner.nextLine();
            if (s.matches("[a-zA-Z\\s]+") && s.length() == 3) {
                patente.append(s);
                break;
            } else {
                System.out.println("Ingresar un dato valido.");
            }
        }
        Integer numero;
        String num;
        while (true) {
            numero = ingresarXInteger("numeros");
            num = String.valueOf(numero);
            if (num.length() == 3) {
                patente.append(num);
                scanner.nextLine(); // buffer
                break;
            } else {
                System.out.println("Ingrese solamente 3 numeros.");
            }
        }

        return patente.toString().toUpperCase();
    }
    // Consola estática que nos servira con todas las clases del programa
}