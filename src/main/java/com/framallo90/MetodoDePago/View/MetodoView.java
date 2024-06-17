package com.framallo90.MetodoDePago.View;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.consola.Consola;
import static com.framallo90.MetodoDePago.Model.Entity.MetodoDePago.INTERES_MENSUAL;

public class MetodoView {
    public MetodoView() {
    }
    public MetodoDePago cargarMetodoDP(Double precioDelVehiculo)
    {
        Integer cuotas;
        Double precioFinanciado;
        while (true)
        {
            System.out.println("Como desea abonar?\n1. Credito\n2. Contado/Debito");
            Integer pago = Consola.ingresarXInteger("opción");
            switch (pago)
            {
                case 1:
                    cuotas = cantidadDeCuotas();
                    precioFinanciado = calcularPrecioFinal(precioDelVehiculo,cuotas);
                    return new MetodoDePago("credito",cuotas,precioFinanciado);
                case 2:
                    cuotas = 1;
                    precioFinanciado = precioDelVehiculo;
                    return new MetodoDePago("un pago",cuotas,precioFinanciado);
            }
            System.out.println("Ingrese una opcion válida.");
        }
    }
    private Double calcularPrecioFinal(Double precioVehiculo, Integer cantCuotas)
    {
        return precioVehiculo + precioVehiculo * (cantCuotas*INTERES_MENSUAL) / 100;
    }
    private Integer cantidadDeCuotas()
    {
        Integer cuotas;
        while (true) {
            System.out.println("Opciones de cuotas disponibles:\n12\n24\n36\n48");
            cuotas = Consola.ingresarXInteger("cant. cuotas deseadas:");
            if (validarCuotas(cuotas)) {
                return cuotas;
            }
            else
                System.out.println("Ingrese una opcion válida.");
        }
    }
    private boolean validarCuotas(Integer cuotas)
    {
        return cuotas == 12 || cuotas == 24 || cuotas == 36 || cuotas == 48;
        /// Si se ingreso bien, devuelve true, sino false.
    }

    public void printMenuModifMDP(){
        System.out.println("""
                desea cambiar la forma de pago?
                1.si
                2.no""");
    }
}
