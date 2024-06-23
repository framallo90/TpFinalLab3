/**
 * Esta clase proporciona métodos estáticos para la interacción con el usuario a través de la consola.
 *
 * @autor Framballo90
 * @since v1.0
 */
package com.framallo90.consola;

import java.util.Scanner;

public class Consola {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
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

    public static void soutAlertString(String string){
        System.err.println(ANSI_RED + string + ANSI_RESET);
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
     * Solicita al usuario que ingrese un String.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return El String ingresado por el usuario.
     */
    public static String ingresarXStringSimple(String x) {
        while (true) {
            System.out.println("Ingresar " + x + ": ");
            return scanner.nextLine();
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
        Integer numero;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("El dato ingresado no es valido. Por favor, ingrese un número entero:");
                scanner.next(); // Limpiar la entrada no válida
            }

            numero = scanner.nextInt();
            scanner.nextLine(); // buffer
            if (numero < 0) {
                System.out.print("No se aceptan numeros negativos, ingrese una opcion valida... -> ");
            }
        } while (numero < 0);

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
            System.out.println("Ingresar " + x + " (letras): ");
            String s = scanner.nextLine();
            if (s.matches("[a-zA-Z]{3}")) {
                patente.append(s.toUpperCase());
                break;
            } else {
                System.out.println("Ingresar un dato válido (3 letras).");
            }
        }

        while (true) {
            Integer numero = ingresarXInteger("numeros (3 dígitos)");
            String num = String.format("%03d", numero); // Asegura que el número tenga 3 dígitos
            if (num.length() == 3) {
                patente.append(num);
                break;
            } else {
                System.out.println("Ingrese solamente 3 números.");
            }
        }

        return patente.toString().toUpperCase();
    }

    /* Metodo viejo patente, no permitia ingresar los numeros empezando con '0'
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
                break;
            } else {
                System.out.println("Ingrese solamente 3 numeros.");
            }
        }

        return patente.toString().toUpperCase();
    }
    */

    /**
     * Imprime el menú para el vendedor en la consola.
     */
    public static void printMenuVendedor() {
        System.out.println("""
                MENÚ VENDEDOR
                1. Gestión clientes
                2. Gestión ventas
                3. Stock carros
                0. Cerrar sesión
                """);
    }

    /**
     * Imprime el menú para el administrador en la consola.
     */
    public static void printMenuAdministrador() {
        System.out.println("""
                MENÚ ADMINISTRADOR
                1. Gestión clientes
                2. Gestión ventas
                3. Gestión carros
                4. Gestión usuarios
                0. Cerrar sesión
                """);
    }

    /**
     * Imprime el menú de gestión de clientes en la consola.
     */
    public static void gestionClientes() {
        System.out.println("""
                GESTIÓN CLIENTES
                1. agregar cliente
                2. modificar cliente
                3. remover cliente
                4. ver lista de clientes
                0. salir
                """);
    }

    /**
     * Imprime el menú de gestión de ventas en la consola.
     */
    public static void gestionVentas() {
        System.out.println("""
                GESTIÓN VENTAS
                1. iniciar venta
                2. ver historial de ventas
                0. salir
                """);
    }

    /**
     * Imprime el menú de gestión de carros en la consola.
     */
    public static void gestionCarros() {
        System.out.println("""
                GESTIÓN CARROS
                1. agregar
                2. modificar
                3. remover
                4. búsqueda
                5. mostrar lista
                0. salir
                """);
    }

    /**
     * Imprime el menú de gestión de usuarios en la consola.
     */
    public static void gestionUsuarios() {
        System.out.println("""
                GESTIÓN USUARIOS
                1. agregar usuario
                2. modificar usuario
                3. remover usuario
                0. salir
                """);
    }

    /**
     * Imprime el menú de inicio de sesión en la consola.
     */
    public static void printMenuLogin() {
        System.out.println("""
                <<<<<<<<<<¡BIENVENIDO!>>>>>>>>>
                1. Iniciar sesión
                          0. Salir del programa
                < < < < < < < < > > > > > > > >
                """);
    }
}
