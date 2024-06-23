package com.framallo90.Automovil.API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que proporciona métodos para interactuar con una API de automóviles.
 * Permite obtener marcas, modelos, años y precios de automóviles utilizando la API de FIPE.
 */
public class ApiAutomovilService {

    private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/carros/";

    /**
     * Realiza una solicitud HTTP GET a la URL especificada y devuelve la respuesta como String.
     * @param url URL a la cual se realizará la solicitud GET.
     * @return String con el cuerpo de la respuesta recibida desde la URL.
     * @throws IOException si ocurre un error al realizar la solicitud HTTP o si la respuesta no es JSON.
     */
    private String getStringFromUrl(String url) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            // Verifica si la respuesta es un HTML en lugar de JSON
            if (responseBody.startsWith("<!DOCTYPE") || responseBody.startsWith("<html")) {
                throw new IOException("Respuesta HTML recibida en lugar de JSON. URL: " + url);
            }

            return responseBody;
        }
    }

    /**
     * Obtiene un mapa de marcas de automóviles disponibles.
     * @return Mapa donde las claves son los códigos de las marcas y los valores son los nombres de las marcas.
     * @throws IOException si hay un error al realizar la solicitud HTTP o al procesar la respuesta JSON.
     */
    public Map<Integer, String> obtenerMarcas() throws IOException {
        String url = BASE_URL + "marcas";
        String response = getStringFromUrl(url);

        JsonReader jsonReader = new JsonReader(new StringReader(response));
        jsonReader.setLenient(true);

        JsonArray jsonArray = JsonParser.parseReader(jsonReader).getAsJsonArray();
        Map<Integer, String> marcas = new HashMap<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            int id = jsonObject.get("codigo").getAsInt();
            String nombre = jsonObject.get("nome").getAsString();
            marcas.put(id, nombre);
        }
        return marcas;
    }

    /**
     * Obtiene un mapa de modelos de automóviles para una marca específica.
     * @param marcaId ID de la marca para la cual se desean obtener los modelos.
     * @return Mapa donde las claves son los códigos de los modelos y los valores son los nombres de los modelos.
     * @throws IOException si hay un error al realizar la solicitud HTTP o al procesar la respuesta JSON.
     */
    public Map<Integer, String> obtenerModelos(int marcaId) throws IOException {
        String url = BASE_URL + "marcas/" + marcaId + "/modelos";
        String response = getStringFromUrl(url);

        JsonReader jsonReader = new JsonReader(new StringReader(response));
        jsonReader.setLenient(true);

        JsonObject jsonObject = JsonParser.parseReader(jsonReader).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("modelos");
        Map<Integer, String> modelos = new HashMap<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject modelObject = jsonArray.get(i).getAsJsonObject();
            int id = modelObject.get("codigo").getAsInt();
            String nombre = modelObject.get("nome").getAsString();
            modelos.put(id, nombre);
        }
        return modelos;
    }

    /**
     * Obtiene un mapa de años de un modelo de automóvil específico para una marca.
     * @param marcaId ID de la marca del automóvil.
     * @param modeloId ID del modelo del automóvil.
     * @return Mapa donde las claves son los códigos de los años y los valores son los nombres de los años.
     * @throws IOException si hay un error al realizar la solicitud HTTP o al procesar la respuesta JSON.
     */
    public Map<String, String> obtenerAnos(int marcaId, int modeloId) throws IOException {
        String url = BASE_URL + "marcas/" + marcaId + "/modelos/" + modeloId + "/anos";
        String response = getStringFromUrl(url);

        JsonReader jsonReader = new JsonReader(new StringReader(response));
        jsonReader.setLenient(true);

        JsonArray jsonArray = JsonParser.parseReader(jsonReader).getAsJsonArray();
        Map<String, String> anos = new HashMap<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject anoObject = jsonArray.get(i).getAsJsonObject();
            String id = anoObject.get("codigo").getAsString();
            String nombre = anoObject.get("nome").getAsString();
            anos.put(id, nombre);
        }
        return anos;
    }

    /**
     * Obtiene el precio de un automóvil específico para una marca, modelo y año dados.
     * @param marcaId ID de la marca del automóvil.
     * @param modeloId ID del modelo del automóvil.
     * @param anoId ID del año del automóvil.
     * @return Precio del automóvil como un valor Double, o null si no se encuentra el precio.
     * @throws IOException si hay un error al realizar la solicitud HTTP o al procesar la respuesta JSON.
     */
    public Double obtenerPrecio(int marcaId, int modeloId, String anoId) throws IOException {
        String url = BASE_URL + "marcas/" + marcaId + "/modelos/" + modeloId + "/anos/" + anoId;
        String response = getStringFromUrl(url);

        JsonReader jsonReader = new JsonReader(new StringReader(response));
        jsonReader.setLenient(true);

        JsonObject jsonObject = JsonParser.parseReader(jsonReader).getAsJsonObject();
        if (jsonObject.has("Valor")) {
            String valorStr = jsonObject.get("Valor").getAsString().replace("R$", "").replace(".", "").replace(",", ".").trim();
            return Double.parseDouble(valorStr);
        }
        return null;
    }
    public Integer buscarKeyMarca(String marcaBuscar) throws IOException {
        String url = BASE_URL + "marcas";
        String response = getStringFromUrl(url);


        JsonReader jsonReader = new JsonReader(new StringReader(response));
        jsonReader.setLenient(true);

        JsonArray jsonArray = JsonParser.parseReader(jsonReader).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject modelObject = jsonArray.get(i).getAsJsonObject();
            int id = modelObject.get("codigo").getAsInt();
            String nombre = modelObject.get("nome").getAsString();
            if(nombre.equals(marcaBuscar)){
                return id;
            }
        }
        return null;
    }
    public Integer buscarKeyModelo(Integer marcaKey,String modelo) throws IOException {
        String url = BASE_URL + "marcas/" + marcaKey + "/modelos";
        String response = getStringFromUrl(url);

        JsonReader jsonReader = new JsonReader(new StringReader(response));
        jsonReader.setLenient(true);

        JsonObject jsonObject = JsonParser.parseReader(jsonReader).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("modelos");


        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject modelObject = jsonArray.get(i).getAsJsonObject();
            int id = modelObject.get("codigo").getAsInt();
            String nombre = modelObject.get("nome").getAsString();
            if(nombre.equals(modelo)){
                return id;
            }
        }
        return null;
    }

}
