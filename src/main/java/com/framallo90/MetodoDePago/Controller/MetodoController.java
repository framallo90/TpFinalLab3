package com.framallo90.MetodoDePago.Controller;

import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.MetodoDePago.View.MetodoView;
import com.framallo90.Venta.Model.Entity.Venta;
import com.framallo90.consola.Consola;

/**
 * Controlador que gestiona la lógica de negocio relacionada con los métodos de pago.
 * Se encarga de interactuar entre la vista (`MetodoView`) y el modelo (`MetodoDePago`).
 *
 * Ejemplo de uso:
 * ```
 * MetodoView metodoView = new MetodoView();
 * MetodoController controller = new MetodoController(metodoView);
 *
 * // Cargar método de pago
 * Double precioVehiculo = 25000.0;
 * MetodoDePago metodo = controller.cargarMDP(precioVehiculo);
 *
 * // Actualizar método de pago
 * controller.updateMDP(metodo, precioVehiculo);
 * ```
 *
 * Permite cargar y actualizar métodos de pago utilizando la vista para interactuar con el usuario.
 *
 * @author Framallo
 * @version 1.0
 */
public class MetodoController {
    private MetodoView metodoView;

    /**
     * Constructor que inicializa el controlador con una instancia de `MetodoView`.
     *
     * @param metodoView La vista asociada al controlador para la gestión de métodos de pago.
     */
    public MetodoController(MetodoView metodoView) {
        this.metodoView = metodoView;
    }

    /**
     * Carga un método de pago utilizando la vista (`MetodoView`) para interactuar con el usuario.
     *
     * @param precioVehiculo El precio del vehículo para el cual se va a cargar el método de pago.
     * @return El método de pago creado o seleccionado por el usuario.
     */
    public MetodoDePago cargarMDP(Double precioVehiculo) {
        return metodoView.cargarMetodoDP(precioVehiculo);
    }

    /**
     * Actualiza un método de pago existente utilizando la vista (`MetodoView`) para interactuar con el usuario.
     *

     * @param precioVehiculo El precio del vehículo para el cual se va a actualizar el método de pago.
     */
    public void updateMDP(Venta venta, Double precioVehiculo) {
        Integer eleccion;

        // Imprime el menú de modificación de método de pago en la vista.
        this.metodoView.printMenuModifMDP();

        // Solicita al usuario que ingrese la elección (opción del menú).
        eleccion = Consola.ingresarXInteger("una opcion");

        // Si la elección es 2, sale del método sin hacer cambios.
        if (eleccion == 2) return;
            // Si la elección es 1, carga un nuevo método de pago y lo asigna al métodoDePago actual.
        else if (eleccion == 1)
            venta.setMetodoDePago(this.cargarMDP(precioVehiculo));
    }
}
