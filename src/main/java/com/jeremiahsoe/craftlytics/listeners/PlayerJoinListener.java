package com.jeremiahsoe.craftlytics.listeners;

import com.jeremiahsoe.craftlytics.api_client.APIClient;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoinListener implements Listener {
    private final APIClient apiClient;

    public PlayerJoinListener(){
        this.apiClient = new APIClient();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String playerUuid = player.getUniqueId().toString();
        String playerName = player.getName();

        Bukkit.getScheduler().runTaskAsynchronously(player.getServer().getPluginManager().getPlugin("Craftlytics"),
                () -> apiClient.sendPlayerStats(playerUuid, playerName));
    }
}
