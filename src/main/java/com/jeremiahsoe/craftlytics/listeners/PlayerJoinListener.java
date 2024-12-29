package com.jeremiahsoe.craftlytics.listeners;

import com.jeremiahsoe.craftlytics.services.PlayerService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoinListener implements Listener {
    private final PlayerService playerService;

    public PlayerJoinListener(){
        this.playerService = new PlayerService();
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String playerUuid = player.getUniqueId().toString();
        String playerName = player.getName();
        Bukkit.getScheduler().runTaskAsynchronously(player.getServer().getPluginManager().getPlugin("Craftlytics"), () -> {
            playerService.updateOrInsertPlayer(playerUuid, playerName);
        });
    }

}
