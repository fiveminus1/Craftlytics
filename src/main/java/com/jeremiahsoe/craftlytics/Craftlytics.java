package com.jeremiahsoe.craftlytics;

import com.jeremiahsoe.craftlytics.database.DatabaseManager;
import com.jeremiahsoe.craftlytics.listeners.BiomeExplorationListener;
import com.jeremiahsoe.craftlytics.listeners.PlayerJoinListener;
import com.jeremiahsoe.craftlytics.listeners.PlayerKillListener;
import org.bukkit.plugin.java.JavaPlugin;


public final class Craftlytics extends JavaPlugin{
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        databaseManager = DatabaseManager.getInstance();
        databaseManager.init();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new BiomeExplorationListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerKillListener(), this);

        getLogger().info("Craftlytics has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (databaseManager != null) {
            databaseManager.close();
            getLogger().info("Craftlytics has been disabled!");
        }
    }


}
