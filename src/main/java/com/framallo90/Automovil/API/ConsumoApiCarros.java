package com.framallo90.Automovil.API;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ConsumoApiCarros {

    public class CarBrandFetcher {

        private static final OkHttpClient client = new OkHttpClient();
        private static final String BRANDS_URL = "https://carapi.app/v1/makes";

        public static void main(String[] args) throws IOException {
            Request request = new Request.Builder()
                    .url(BRANDS_URL)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    System.err.println("Failed to fetch car brands: " + response.message());
                    return;
                }

                // Parse JSON response using a JSON parser library (e.g., Gson, Jackson)
                // Extract car brands from the parsed JSON structure
                // Process the extracted car brands (e.g., display, store, analyze)
            }
        }
    }

}
