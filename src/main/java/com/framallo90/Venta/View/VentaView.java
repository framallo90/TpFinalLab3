package com.framallo90.Venta.View;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.Venta.Model.Entity.Venta;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
public class VentaView {
    public Venta generarVenta(Empleados empleados,
                              Comprador comprador,
                              Automovil automovil,
                              LocalDate fecha,
                              MetodoDePago transaccion
                        ){
        return new Venta(empleados, comprador, automovil, fecha, transaccion);
    }
    public void mostrarVenta(Venta venta){
        System.out.println(venta.toString());
    }
    public void mostrarHistorial(Map<Integer, Venta> map){
        if (map.isEmpty()){
            System.out.println("Aún no hay ventas registradas...");
            return;
        }
        for (Map.Entry<Integer,Venta> entry:map.entrySet()){
            System.out.println(entry.getValue().toString());
        }
    }
    public void mostrarFecha(LocalDate fecha){
        String dateFormat = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        // Format the LocalDate object to the specified format
        String formattedDate = fecha.format(formatter);
        // Display the formatted date
        System.out.println("Fecha formateada: " + formattedDate);
    }

    public void printMenuModifVenta(){
        System.out.println("""
                datos de la venta
                1. empleado
                2. comprador
                3. automovil
                4. fecha
                5. metodo de pago
                
                0. salir
                """);
    }

    public void printMenuModifFecha(){
        System.out.println("\nMenú de modificación de fecha:");
        System.out.println("1. Cambiar día");
        System.out.println("2. Cambiar mes");
        System.out.println("3. Cambiar año");
        System.out.println("0. Salir");
    }

    public void printMenuVentas(){
        System.out.println("""
                Menu ventas
                1. agregar
                2. mostrar
                3. modificar
                4. remover
                5. mostrar todas
                0. salir
                """);
    }
}
