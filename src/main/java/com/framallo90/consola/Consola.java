package com.framallo90.consola;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.DoublePredicate;

public class Consola {
    private static final Scanner scanner = new Scanner(System.in);
    public static String ingresarXString(String x){
        while (true){
            System.out.println("Ingresar "+x+": ");
            String s = scanner.nextLine();
            if (s.matches("[a-zA-Z\\s]+"))
                return s;
            else System.out.println("Ingresar un dato valido.");
        }
    }
    public static Integer ingresarXInteger(String x){
        System.out.println("Ingresar " + x + ": ");
        while (!scanner.hasNextInt()) {
            System.out.println("El dato ingresado no es valido. Por favor, ingrese un número entero:");
            scanner.next(); // Limpiar la entrada no válida
        }
        Integer numero = scanner.nextInt();
        if (numero< 0){
            throw new IllegalArgumentException("El numero debe ser mayor o igual a 0.");
        }
        return numero;
    }

    public static Double ingresarXdouble(String x){
        System.out.println("Ingresar "+ x +": " );
        while (!scanner.hasNextDouble()){
            System.out.println("El dato ingresado no es valido. Por favor, ingrese un double");
            scanner.next();
        }
        Double numero = scanner.nextDouble();
        return numero;
    }

    public static String patente(String x){
        StringBuilder patente = new StringBuilder();
        while (true){
            System.out.println("Ingresar "+x+": ");
            String s = scanner.nextLine();
            if (s.matches("[a-zA-Z\\s]+") && s.length() == 3){
                    patente.append(s);
                    break;
            }
            else System.out.println("Ingresar un dato valido.");
        }

        while (true){
            Integer numero = ingresarXInteger("numeros");
            String num = String.valueOf(numero);
            if(num.length()==3){
                patente.append(num);
                break;
            }else {
                System.out.println("Ingrese solamente 3 numeros.");
            }
        }
        return patente.toString().toUpperCase();
    }
    // Consola estática que nos servira con todas las clases del programa
}
