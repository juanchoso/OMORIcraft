package com.juancho.spigot.omorimod;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import com.juancho.spigot.omorimod.utils.Entity;
import com.juancho.spigot.omorimod.utils.emotions.Angry;

public class OnEntityExplodeEvent implements Listener {

    @EventHandler
    public void OnEntityExplode(org.bukkit.event.entity.EntityExplodeEvent event) {
        org.bukkit.entity.Entity entity = event.getEntity();
        // Si es un Creeper
        if (entity instanceof org.bukkit.entity.Creeper) {
            org.bukkit.entity.Creeper creeper = (org.bukkit.entity.Creeper) entity;
            // Obtener su emoción
            Entity omori_entity = new Entity(creeper);
            // Si es enojado
            if (omori_entity.getEmotion().rawName().equals("ANGRY")) {
                // Hacer la explosión enorme
                event.setCancelled(true);
                creeper.getLocation().getWorld().createExplosion(creeper.getLocation(), 30);
            }
            if (omori_entity.getEmotion().rawName().equals("SAD")) {
                // Cancela el evento de explosión pero se enoja
                event.setCancelled(true);
                creeper.getLocation().getWorld().createExplosion(creeper.getLocation(), 2);
            }
            if (omori_entity.getEmotion().rawName().equals("HAPPY")) {
                // Pone a todas las entidades cercanas felices
                for( org.bukkit.entity.Entity nearby_entity : creeper.getNearbyEntities(30, 30, 30)) {
                    if (nearby_entity instanceof LivingEntity) {
                        Entity nearby_omori_entity = new Entity(nearby_entity);
                        nearby_omori_entity.updateEmotion(new com.juancho.spigot.omorimod.utils.emotions.Happy());
                        nearby_omori_entity.broadcastEmotion(30);
                    }
                }
            }

            

        }
    }
    
}
