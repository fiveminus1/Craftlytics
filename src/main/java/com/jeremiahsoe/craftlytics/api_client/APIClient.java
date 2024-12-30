package com.jeremiahsoe.craftlytics.api_client;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class APIClient{
    private static final String API_BASE_URL = "http://localhost:8080/api";

    public void sendPlayerStats(String playerUuid, String playerName){
        String url = API_BASE_URL + "/players?playerUuid=" + playerUuid + "&playerName=" + playerName;
        System.out.println("URL for request: " + url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpPost request = new HttpPost(url);
            request.setHeader("Content-Type", "application/json");

            httpClient.execute(request, response -> {
                System.out.println("Response: " + EntityUtils.toString(response.getEntity()));
                return null;
            });
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}