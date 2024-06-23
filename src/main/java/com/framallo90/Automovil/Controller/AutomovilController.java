package com.framallo90.Automovil.Controller;
import com.framallo90.Automovil.API.ApiAutomovilService;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Excepciones.EmptyAStockException;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
/**
 * Controlador que maneja las operaciones relacionadas con los automóviles, interactuando con la API y la vista.
 */
public class AutomovilController {
    private final AutomovilRepository automovilRepository;
    private final AutomovilView automovilView;
    private final ApiAutomovilService apiAutomovilService;

    /**
     * Constructor del controlador de automóviles.
     * @param automovilRepository Repositorio de automóviles donde se almacenan los automóviles.
     * @param automovilView Vista que maneja la interfaz de usuario relacionada con los automóviles.
     */
    public AutomovilController(AutomovilRepository automovilRepository, AutomovilView automovilView) {
        this.automovilRepository = automovilRepository;
        this.automovilView = automovilView;
        this.apiAutomovilService = new ApiAutomovilService();
    }

    /**
     * Método privado para seleccionar una marca utilizando la API de automóviles.
     * @return Entrada de mapa que representa la pareja ID de marca y nombre de marca seleccionada.
     */
    private Map.Entry<Integer, String> seleccionarMarca() {
        try {
            Map<Integer, String> marcas = apiAutomovilService.obtenerMarcas();
            System.out.println("Seleccione una marca:");
            for (Map.Entry<Integer, String> entry : marcas.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            int marcaId = Consola.ingresarXInteger("ID de la marca");
            if (!marcas.containsKey(marcaId)) {
                Consola.soutAlertString("ID de marca no válido. Intente nuevamente.");
                return seleccionarMarca();
            }
            String marcaNombre = marcas.get(marcaId);
            return Map.entry(marcaId, marcaNombre);
        } catch (IOException e) {
            Consola.soutAlertString("Error al obtener las marcas: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método privado para seleccionar un modelo utilizando la API de automóviles.
     * @param marcaId ID de la marca para la cual se desea seleccionar el modelo.
     * @return Entrada de mapa que representa la pareja ID de modelo y nombre de modelo seleccionado.
     */
    private Map.Entry<Integer, String> seleccionarModelo(int marcaId) {
        try {
            Map<Integer, String> modelos = apiAutomovilService.obtenerModelos(marcaId);
            System.out.println("Seleccione un modelo:");
            for (Map.Entry<Integer, String> entry : modelos.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            int modeloId = Consola.ingresarXInteger("ID del modelo");
            if (!modelos.containsKey(modeloId)) {
                Consola.soutAlertString("ID de modelo no válido. Intente nuevamente.");
                return seleccionarModelo(marcaId);
            }
            String modeloNombre = modelos.get(modeloId);
            return Map.entry(modeloId, modeloNombre);
        } catch (IOException e) {
            Consola.soutAlertString("Error al obtener los modelos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método privado para seleccionar un año utilizando la API de automóviles.
     * @param marcaId ID de la marca del automóvil.
     * @param modeloId ID del modelo del automóvil.
     * @return ID del año seleccionado.
     */
    private String seleccionarAno(int marcaId, int modeloId) {
        try {
            Map<String, String> anos = apiAutomovilService.obtenerAnos(marcaId, modeloId);
            System.out.println("Seleccione un año:");
            for (Map.Entry<String, String> entry : anos.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            while (true) {
                String anoId = Consola.ingresarXStringSimple("ID del año");
                if (anos.containsKey(anoId)) {
                    return anoId;
                } else {
                    Consola.soutAlertString("Ingresar un dato válido.");
                }
            }
        } catch (IOException e) {
            Consola.soutAlertString("Error al obtener los años: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método privado para obtener el precio de un automóvil utilizando la API de automóviles.
     * @param marcaId ID de la marca del automóvil.
     * @param modeloId ID del modelo del automóvil.
     * @param anoId ID del año del automóvil.
     * @return Precio del automóvil.
     */
    private Double obtenerPrecio(int marcaId, int modeloId, String anoId) {
        try {
            return apiAutomovilService.obtenerPrecio(marcaId, modeloId, anoId);
        } catch (IOException e) {
            Consola.soutAlertString("Error al obtener el precio: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método público para agregar un automóvil al stock utilizando la API de automóviles.
     */
    public void agregarAutomovilAlStock() {
        Map.Entry<Integer, String> marca = seleccionarMarca();
        if (marca == null) return;

        Map.Entry<Integer, String> modelo = seleccionarModelo(marca.getKey());
        if (modelo == null) return;

        String anoId = seleccionarAno(marca.getKey(), modelo.getKey());
        if (anoId == null) return;

        Double precio = obtenerPrecio(marca.getKey(), modelo.getKey(), anoId);
        if (precio == null) return;

        String patente = automovilView.ingresoPatente();

        int anio = Integer.parseInt(anoId.split("-")[0]);

        Automovil nuevo = new Automovil(marca.getValue(), modelo.getValue(), precio, patente, anio);
        automovilRepository.add(nuevo);
    }

    /**
     * Método público para borrar un automóvil del stock utilizando su ID.
     */
    public void borrarAutomovilEnStock() {
        try {
            automovilRepository.remove(Consola.ingresarXInteger("ID"));
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }

    /**
     * Elimina un automóvil del stock basado en su ID.
     *
     * @param id El ID del automóvil que se desea eliminar del stock.
     */
    public void borrarAutomovilEnStockPorId(Integer id) {
        try {
            automovilRepository.remove(id);
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }
    /**
     * Método público para modificar un automóvil en el stock utilizando su ID.
     */

    public void modificar(Automovil automovil) throws IOException {
        System.out.println("1. Marca( y por consecuente modelo)\n2. Modelo\n3. Precio\n4. Patente \n5. Anio");
        Integer IDMod = Consola.ingresarXInteger("numero");
        switch (IDMod) {
            case 1:

                automovil.setMarca(seleccionarMarca().getValue());
                Integer keyMarca = apiAutomovilService.buscarKeyMarca(automovil.getMarca());
                automovil.setModelo(seleccionarModelo(keyMarca).getValue());
                break;
            case 2:
                Integer keyMarc = apiAutomovilService.buscarKeyMarca(automovil.getMarca());
                automovil.setModelo(seleccionarModelo(keyMarc).getValue());
                break;
            case 3:

                automovil.setPrecio(Consola.ingresarXdouble("precio"));
                break;
            case 4:
                automovil.setPatente(Consola.patente("patente"));
                break;
            case 5:
                Integer keyMark = apiAutomovilService.buscarKeyMarca(automovil.getMarca());
                Integer keyModel = apiAutomovilService.buscarKeyModelo(keyMark,automovil.getModelo());
                String anoId = seleccionarAno(keyMark,keyModel);

                automovil.setAnio(Integer.parseInt(anoId.split("-")[0]));
                break;
            default:
                Consola.soutAlertString("Opcion no valida");
                break;

        }
    }

    /**
     * Método público para buscar un automóvil en el stock utilizando su ID.
     * @param id ID del automóvil a buscar.
     * @return Automóvil encontrado, o null si no se encuentra.
     */
    public Automovil find(Integer id) {
        return automovilRepository.find(id);
    }


    /**
     * Método público para mostrar todos los automóviles en el stock.
     */
    public void mostrarAutomovilesEnStock() {
        try {
            automovilView.mostrarAutomoviles(automovilRepository.getAutomovilList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Método público para buscar automóviles en el stock utilizando filtros.
     */


    /**
     * Método público que muestra el menú de administración de automóviles.
     */
    public void menuAutomovilAdmin() {
        int eleccion;

        while (true) {
            automovilView.printMenuAutomovilAdmin();
            eleccion = Consola.ingresarXInteger("elección");

            switch (eleccion) {
                case 0: // Salir
                    return;
                case 1: // Agregar
                    agregarAutomovilAlStock();
                    break;
                case 2: // Remover
                    mostrarAutomovilesEnStock();
                    borrarAutomovilEnStock();
                    break;
                case 3: // Mostrar detalle de automóvil por ID
                    mostrarAutomovilesEnStock();
                    Automovil find = find(Consola.ingresarXInteger("ID del automóvil"));
                    if (find == null) {
                        Consola.soutAlertString("No se ha encontrado un automóvil con el ID ingresado.");
                    } else {
                        Consola.soutString(find.toString());
                    }
                    break;
                case 4: // Ver lista filtrada
                    try{
                        automovilView.buscarAutomovilesXFiltro(automovilRepository.getAutomovilList());
                    }catch (EmptyAStockException e) {
                        Consola.soutAlertString(e.getMessage());
                    }

                    break;
                case 5:
                    Automovil aModificar;
                    mostrarAutomovilesEnStock();
                    aModificar = automovilRepository.find(Consola.ingresarXInteger("ID del automóvil"));
                    if(aModificar != null){
                        try {
                            modificar(aModificar);
                            automovilRepository.update(aModificar.getId(),aModificar);
                        } catch (IOException ex) {
                            Consola.soutAlertString(ex.getMessage());
                        } catch (InvalidIdNotFound e) {
                            Consola.soutAlertString(e.getMessage());
                        }
                    }
                    break;
                default: // Opción no reconocida
                    Consola.soutAlertString("El dato ingresado no es válido. Reintentar.");
                    break;
            }
        }
    }

    /**
     * Método público que muestra el menú de vendedor de automóviles.
     */
    public void menuAutomovilVendedor() {
        int eleccion;

        while (true) {
            automovilView.printMenuAutomovilVendedor();
            eleccion = Consola.ingresarXInteger("elección");

            switch (eleccion) {
                case 0: // Salir
                    return;
                case 1: // Mostrar detalle de automóvil por ID
                    mostrarAutomovilesEnStock();
                    Automovil find = find(Consola.ingresarXInteger("ID del automóvil"));
                    if (find != null) {
                        Consola.soutString(find.toString());
                    }
                    break;
                case 2: // Ver stock de automóviles
                    mostrarAutomovilesEnStock();
                    break;
                case 3: // Ver lista filtrada
                    try {
                        automovilView.buscarAutomovilesXFiltro(automovilRepository.getAutomovilList());
                    } catch (EmptyAStockException e) {
                        Consola.soutAlertString(e.getMessage());
                    }
                    break;
                default: // Opción no reconocida
                    Consola.soutAlertString("El dato ingresado no es válido. Reintentar.");
                    break;
            }
        }
    }

    /**
     * Método público que verifica si el repositorio de automóviles está vacío.
     * @return true si el repositorio está vacío, false en caso contrario.
     */
    public boolean isRepoEmpty() {
        return this.automovilRepository.isEmpty();
    }
}