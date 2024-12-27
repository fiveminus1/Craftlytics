package com.jeremiahsoe.craftlytics;

import com.jeremiahsoe.craftlytics.database.DatabaseManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class Craftlytics extends JavaPlugin implements Listener {

    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        databaseManager = new DatabaseManager();
        databaseManager.init();

        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        System.out.println("logging player move event");
        String playerUuid = event.getPlayer().getUniqueId().toString();
        String biomeName = event.getTo().getBlock().getBiome().toString();
        String location = event.getTo().getBlockX() + "," +  event.getTo().getBlockY() + "," + event.getTo().getBlockZ();

        databaseManager.logBiomeExploration(playerUuid, biomeName, location);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (databaseManager != null){
            databaseManager.close();
        }
    }
}
