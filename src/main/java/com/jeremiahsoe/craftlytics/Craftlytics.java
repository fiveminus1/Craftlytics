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


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (databaseManager != null){
            databaseManager.close();
        }
    }
}
