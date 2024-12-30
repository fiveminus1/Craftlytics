package com.jeremiahsoe.craftlytics.listeners;

import com.jeremiahsoe.craftlytics.api_client.APIClient;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerKillListener implements Listener{
    private final APIClient apiClient;

    public PlayerKillListener(){
        this.apiClient = new APIClient();
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        Player killedPlayer = event.getEntity();
        EntityDamageEvent damageEvent = killedPlayer.getLastDamageCause();

        if(damageEvent instanceof EntityDamageByEntityEvent){ // if damage caused by another entity
            EntityDamageByEntityEvent entityDamageEvent = (EntityDamageByEntityEvent) damageEvent;
            Entity damager = entityDamageEvent.getDamager();

            if(damager instanceof Player){
                Player killer = (Player) damager;
                Bukkit.getScheduler().runTaskAsynchronously(killedPlayer.getServer().getPluginManager().getPlugin("Craftlytics"),
                        () -> apiClient.logPlayerKill(killer.getUniqueId().toString(), killedPlayer.getUniqueId().toString()));
            }
        }

    }
}
