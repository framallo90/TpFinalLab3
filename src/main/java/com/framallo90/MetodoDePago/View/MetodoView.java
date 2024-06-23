package com.framallo90.MetodoDePago.View;

import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.consola.Consola;
import static com.framallo90.MetodoDePago.Model.Entity.MetodoDePago.INTERES_MENSUAL;

/**
 * Vista que proporciona métodos para la interacción con el usuario relacionados con los métodos de pago.
 * Permite cargar métodos de pago y mostrar opciones para modificarlos.
 *
 * Ejemplo de uso:
 * ```
 * MetodoView metodoView = new MetodoView();
 *
 * // Cargar método de pago
 * Double precioVehiculo = 25000.0;
 * MetodoDePago metodo = metodoView.cargarMetodoDP(precioVehiculo);
 *
 * // Imprimir menú de modificación de método de pago
 * metodoView.printMenuModifMDP();
 * ```
 *
 * @author Framallo
 * @version 1.0
 */
public class MetodoView {

    /**
     * Constructor por defecto de la clase `MetodoView`.
     */
    public MetodoView() {
    }

    /**
     * Método para cargar un método de pago según la elección del usuario.
     *
     * @param precioDelVehiculo El precio del vehículo para el cual se va a cargar el método de pago.
     * @return El método de pago creado según la elección del usuario.
     */
    public MetodoDePago cargarMetodoDP(Double precioDelVehiculo) {
        Integer cuotas;
        Double precioFinanciado;
        while (true) {
            System.out.println("Como desea abonar?\n1. Crédito\n2. Contado/Debito");
            Integer pago = Consola.ingresarXInteger("opción");
            switch (pago) {
                case 1:
                    cuotas = cantidadDeCuotas();
                    precioFinanciado = calcularPrecioFinal(precioDelVehiculo, cuotas);
                    return new MetodoDePago("crédito", cuotas, precioFinanciado);
                case 2:
                    cuotas = 1;
                    precioFinanciado = precioDelVehiculo;
                    return new MetodoDePago("un pago", cuotas, precioFinanciado);
            }
            System.out.println("Ingrese una opción válida.");
        }
    }

    /**
     * Calcula el precio final financiado del vehículo dado el precio y la cantidad de cuotas.
     *
     * @param precioVehiculo El precio del vehículo a financiar.
     * @param cantCuotas     La cantidad de cuotas para el financiamiento.
     * @return El precio final financiado del vehículo.
     */
    private Double calcularPrecioFinal(Double precioVehiculo, Integer cantCuotas) {
        return precioVehiculo + precioVehiculo * (cantCuotas * INTERES_MENSUAL) / 100;
    }

    /**
     * Muestra las opciones disponibles de cantidad de cuotas y permite al usuario seleccionar una.
     *
     * @return La cantidad de cuotas seleccionada por el usuario.
     */
    private Integer cantidadDeCuotas() {
        Integer cuotas;
        while (true) {
            System.out.println("Opciones de cuotas disponibles:\n12\n24\n36\n48");
            cuotas = Consola.ingresarXInteger("cant. cuotas deseadas:");
            if (validarCuotas(cuotas)) {
                return cuotas;
            } else {
                System.out.println("Ingrese una opción válida.");
            }
        }
    }

    /**
     * Valida que la cantidad de cuotas ingresada sea una opción válida (12, 24, 36 o 48).
     *
     * @param cuotas La cantidad de cuotas a validar.
     * @return true si la cantidad de cuotas es válida, false de lo contrario.
     */
    private boolean validarCuotas(Integer cuotas) {
        return cuotas == 12 || cuotas == 24 || cuotas == 36 || cuotas == 48;
    }

    /**
     * Imprime un menú para modificar el método de pago.
     * Presenta opciones para cambiar la forma de pago o salir sin modificar.
     */
    public void printMenuModifMDP() {
        System.out.println("""
                ¿Desea cambiar la forma de pago?
                1. Sí
                2. No""");
    }
}
