package com.juancho.spigot.omorimod;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.projectiles.ProjectileSource;

import com.juancho.spigot.omorimod.utils.Entity;
import com.juancho.spigot.omorimod.utils.emotions.Neutral;

public class OnEntitySpawnEvent implements Listener {

    public void OnPlayerRespawn(org.bukkit.event.player.PlayerRespawnEvent event) {
        Player entity = event.getPlayer();

        if (entity instanceof Player) {
            // Enviar un consejo
            Player player = (Player) entity;
            ProTips tips = new ProTips();
            String tip = tips.getRandomTip();
            player.sendMessage("[Consejo] " + tip);

        }

        if (event.isBedSpawn()) { 
            Entity omori_entity = new Entity(entity);
            omori_entity.updateEmotion(new Neutral());
        }

    }

    @EventHandler
    public void OnEntitySpawn(org.bukkit.event.entity.EntitySpawnEvent event) {
        org.bukkit.entity.Entity entity = event.getEntity();

        // Si es una flecha
        if (entity instanceof Projectile) {
            Projectile projectile = (Projectile) entity;
            // Si es una flecha de un jugador
            ProjectileSource shooter = projectile.getShooter();
            // Si el disparador es una ENTIDAD
            if (shooter instanceof org.bukkit.entity.Entity) {
                org.bukkit.entity.Entity shooter_entity = (org.bukkit.entity.Entity) shooter;
                Entity omori_entity = new Entity(shooter_entity);
                Entity projectile_entity = new Entity(projectile);
                projectile_entity.setOwner(omori_entity);
                projectile_entity.updateEmotion(omori_entity.getEmotion());
            }

        } else {
            Entity omori_entity = new Entity(entity);
            omori_entity.broadcastEmotion();
        }
    }
}
