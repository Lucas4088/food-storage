package com.wat.foodmanager.restservice.recipes;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@PropertySource({"classpath:recipes-api.properties"})
public class Food2Fork {

    @Value("${food2fork.api.key}")
    private String _apiKey;

    public Food2Fork() {

    }

    public RecipeFood2Fork getRecipe(String id) throws IOException {
        String json = getJsonFromUrl("http://food2fork.com/api/get?key=" + _apiKey + "&rId=" + id);
        Genson genson = new GensonBuilder().useRuntimeType(true).create();
        return genson.deserialize(json, RecipeContainer.class).recipe;
    }

    public SearchResult search(String query, char sort, int page) throws IOException {
        String json = getJsonFromUrl("http://food2fork.com/api/search?key=" + _apiKey
                + "&q=" + query + "&sort=" + sort + "&page=" + page);
        Genson genson = new GensonBuilder().useRuntimeType(true).create();
        return genson.deserialize(json, SearchResult.class);
    }

    private String getJsonFromUrl(String s) throws IOException {
        HttpURLConnection urlConnection = null;
        String json = "";
        try {
            System.setProperty("http.agent", "Chrome");
            URL url = new URL(s);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(url.openStream());

            int d = reader.read();
            while (d != -1) {
                json += (char) d;
                d = reader.read();
            }
        } catch (IOException e) {
            throw new IOException("There was an error reading from the url. Check your parameters, and that the endpoint is online.");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return json;
    }

}
