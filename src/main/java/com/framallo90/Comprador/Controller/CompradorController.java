

package com.framallo90.Comprador.Controller;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Comprador.Model.Repository.CompradorRepository;
import com.framallo90.Comprador.View.CompradorView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;

public class CompradorController {
    CompradorView compradorView;
    CompradorRepository compradorRepository;

    public CompradorController(CompradorView compradorView, CompradorRepository compradorRepository) {
        this.compradorView = compradorView;
        this.compradorRepository = compradorRepository;
    }

    public void compradorMenu(){
        int opt;
        do {
            System.out.println("MENU CLIENTES");
            System.out.println("1. Agregar cliente.");
            System.out.println("2. Modificar cliente.");
            System.out.println("3. Elimniar cliente.");
            System.out.println("4. Buscar un cliente");
            System.out.println("5. Historial de clientes.");
            System.out.println("6. SALIR.");
            opt = Consola.ingresarXInteger("opcion");
            switch (opt){
                case 1:
                    add();
                    break;

                case 2:
                    update();
                    break;

                case 3:
                    remove();
                    break;

                case 4:
                    show();
                    break;

                case 5:
                    verHisorial();
                    break;

                case 6:
                    System.out.println("Saliendo....");
                    break;

                default:
                    System.out.println("Opcion invalida vuelva a intentarlo");
                    break;
            }
        }while (opt!=6);
    }

    public void add(){
        String nombre = Consola.ingresarXString("nombre");
        String apellido = Consola.ingresarXString("apellido");
        Integer dni = Consola.ingresarXInteger("dni");
        String email = compradorView.ingresoEmail();

        Comprador comprador = new Comprador(nombre,apellido,dni,email);
        this.compradorRepository.add(comprador);
    }

    public void remove(){
        try {
            compradorRepository.remove(Consola.ingresarXInteger("id"));
        }catch (InvalidIdNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    public void update()
    {
        int opt;
        Comprador comprador = compradorRepository.find(Consola.ingresarXInteger("id"));
        do{
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. DNI");
            System.out.println("4. E-Mail");
            System.out.println("5. SALIR.");
            System.out.println("Ingrese el id del cliente a modificar: ");
            opt = Consola.ingresarXInteger("elemento a modificar");
            switch (opt){
                case 1:
                    compradorRepository.cambioNombre(comprador,Consola.ingresarXString("nuevo nombre"));
                    break;

                case 2:
                    compradorRepository.cambioApellido(comprador,Consola.ingresarXString("nuevo apellido"));
                    break;

                case 3:
                    compradorRepository.cambioDni(comprador, Consola.ingresarXInteger("nuevo DNI"));
                    break;

                case 4:
                    compradorRepository.cambioEmail(comprador, compradorView.ingresoEmail());
                    break;

                case 5:
                    System.out.println("Saliendo....");
                    break;

                default:
                    System.out.println("Opcion invalida, vuelva a intentarlo.");
                    break;
            }
        }while (opt != 5);
    }
    //sobrecarga
    public void update(Comprador comprador)
    {
        int opt;
        do{
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. DNI");
            System.out.println("4. E-Mail");
            System.out.println("5. SALIR.");
            opt = Consola.ingresarXInteger("elemento a modificar");
            switch (opt){
                case 1:
                    compradorRepository.cambioNombre(comprador,Consola.ingresarXString("nuevo nombre"));
                    break;

                case 2:
                    compradorRepository.cambioApellido(comprador,Consola.ingresarXString("nuevo apellido"));
                    break;

                case 3:
                    compradorRepository.cambioDni(comprador, Consola.ingresarXInteger("nuevo DNI"));
                    break;

                case 4:
                    compradorRepository.cambioEmail(comprador, compradorView.ingresoEmail());
                    break;

                case 5:
                    System.out.println("Saliendo....");
                    break;

                default:
                    System.out.println("Opcion invalida, vuelva a intentarlo.");
                    break;
            }
        }while (opt != 5);
    }
    public void show()
    {
        Integer id = Consola.ingresarXInteger("id del comprador buscado");
        Comprador comprador = compradorRepository.find(id);
        if (comprador != null)
            compradorView.muestroUnComprador(comprador);
    }

    public Comprador find (Integer id){
        return compradorRepository.find(id);
    }

    public void verHisorial(){
        compradorView.muestroCompradores(compradorRepository.getListaCompradores());
    }
}
