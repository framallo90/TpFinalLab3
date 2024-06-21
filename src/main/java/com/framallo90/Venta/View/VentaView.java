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
        System.out.println("======================================");
        System.out.println("VentaID: "+venta.getIdVenta());
        System.out.println("Fecha: "+ venta.getFecha());
        System.out.println("Vendedor: "+venta.getEmpleados().getApellido()+", "+venta.getEmpleados().getNombre());
        System.out.println("Cliente: "+venta.getComprador().getApellido()+", "+venta.getComprador().getNombre());
        System.out.println("Detalle de la venta:"+venta.getAutomovil().getMarca()+", "+venta.getAutomovil().getModelo()+", "+venta.getAutomovil().getAnio());
        System.out.println("Precio "+ venta.getAutomovil().getPrecio());
        System.out.println("Metodo de pago: " + venta.getTransaccion().getTipo());
        System.out.println("Cantidad de cuotas: " + venta.getTransaccion().getCuotas());
        System.out.println("Precio Financiado: " + venta.getTransaccion().getPrecioFinanciado());
        System.out.println("======================================");
    }
    public void mostrarHistorial(Map<Integer, Venta> map){
        if (map.isEmpty()){
            System.out.println("Aún no hay ventas registradas...");
            return;
        }
        for (Map.Entry<Integer,Venta> entry:map.entrySet()){
            mostrarVenta(entry.getValue());
        }
    }
    public void printMenuModifVenta(){
        System.out.println("""
                MODIFICAR VENTA
                1. Empleado
                2. Comprador
                3. Automovil
                4. Metodo de pago
                0. Atras
                """);
    }
    public void printMenuVentas(){
        System.out.println("""
                MENU VENTAS
                1. Registrar una venta
                2. Buscar venta
                3. Modificar venta
                4. Remover venta
                5. Mostrar todas
                0. Volver
                """);
    }
}