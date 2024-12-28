package com.jeremiahsoe.craftlytics.listeners;

import com.jeremiahsoe.craftlytics.services.BiomeService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BiomeExplorationListener implements Listener {
    private final BiomeService biomeService;
    private final Map<UUID, String> playerBiomes;

    public BiomeExplorationListener(){
        this.biomeService = new BiomeService();
        this.playerBiomes = new HashMap<>();

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();

        if(from.getBlockX() == to.getBlockX() && from.getBlockZ() == to.getBlockZ()){
            // if the player hasn't moved to a new block, do nothing
            return;
        }
        String currentBiome = to.getBlock().getBiome().toString();
        UUID playerUuid = player.getUniqueId();
        String lastBiome = playerBiomes.get(playerUuid);

        if(!currentBiome.equals(lastBiome)){ //if biome changed
            playerBiomes.put(playerUuid, currentBiome);

            String location = to.getBlockX() + ", " + to.getBlockY() + ", " + to.getBlockZ();
            Bukkit.getScheduler().runTaskAsynchronously(player.getServer().getPluginManager().getPlugin("Craftlytics"), () -> {
               biomeService.logBiomeExploration(playerUuid.toString(), currentBiome, location);
            });
        }

    }
}
