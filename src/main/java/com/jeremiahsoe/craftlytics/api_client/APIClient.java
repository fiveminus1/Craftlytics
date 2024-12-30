package com.jeremiahsoe.craftlytics.api_client;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class APIClient{
    private static final String API_BASE_URL = "http://localhost:8080/api";

    public void sendPlayerStats(String playerUuid, String playerName){
        String url = API_BASE_URL + "/players?playerUuid=" + URLEncoder.encode(playerUuid, StandardCharsets.UTF_8)
                + "&playerName=" + playerName;

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

    public void logBiomeExploration(String playerUuid, String biomeName, String location){
        String url = API_BASE_URL + "/biomes_explored?playerUuid=" + URLEncoder.encode(playerUuid, StandardCharsets.UTF_8)
                + "&biomeName=" + biomeName
                + "&location=" + URLEncoder.encode(location, StandardCharsets.UTF_8);

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

    public void logPlayerKill(String playerUuid, String killedPlayerUuid){
        String url = API_BASE_URL + "/player-kills?playerUuid=" + URLEncoder.encode(playerUuid, StandardCharsets.UTF_8)
                + "&killedPlayerUuid=" + URLEncoder.encode(playerUuid, StandardCharsets.UTF_8);

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