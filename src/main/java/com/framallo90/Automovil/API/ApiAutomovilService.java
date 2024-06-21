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

public class ApiAutomovilService {
    private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/carros/";

    private String getStringFromUrl(String url) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            if (responseBody.startsWith("<!DOCTYPE") || responseBody.startsWith("<html")) {
                throw new IOException("Respuesta HTML recibida en lugar de JSON. URL: " + url);
            }

            return responseBody;
        }
    }

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
}
