package com.juancho.spigot.omorimod;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.juancho.spigot.omorimod.utils.Entity;
import com.juancho.spigot.omorimod.utils.emotions.Angry;
import com.juancho.spigot.omorimod.utils.emotions.Emotion;
import com.juancho.spigot.omorimod.utils.emotions.Sad;

public class OnEntityDeathEvent implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        org.bukkit.entity.Entity entity = event.getEntity();
        // player death puts sad emotion on nearby players
        if (entity instanceof Player) {
            Location l = entity.getLocation();
            Collection<org.bukkit.entity.Entity> entities = l.getWorld().getNearbyEntities(l, 30,30,30);
            for (org.bukkit.entity.Entity e : entities) {
                if (e instanceof Player) {
                    double sadRate = 0.8;
                    Random r = new Random();
                    r.nextDouble();
                    if (r.nextDouble() <= sadRate) {
                        Player p = (Player) e;
                        Entity omori_entity = new Entity(p);
                        omori_entity.updateEmotion(new com.juancho.spigot.omorimod.utils.emotions.Sad());
                    };
                }
            }
            

        }
        
        // monster death puts angry emotion on nearby monsters
        if (entity instanceof Monster) {
            Location l = entity.getLocation();
            Collection<org.bukkit.entity.Entity> entities = l.getWorld().getNearbyEntities(l, 25,25,25);
            for (org.bukkit.entity.Entity e : entities) {
                if (e instanceof Monster) {
                    double angryRate = 0.3;
                    Random r = new Random();
                    if (r.nextDouble() <= angryRate) {
                        boolean angry_or_sad = r.nextBoolean();
                        Emotion new_emotion = angry_or_sad ? new Angry() : new Sad();
                        Entity omori_entity = new Entity(e);
                        omori_entity.updateEmotion(new_emotion);
                    };
                }
            }
        }
    
        // friendly mob death puts sad emotion on nearby players
        if (!(entity instanceof Monster) && !(entity instanceof Player)) {
            Location l = entity.getLocation();
            Collection<org.bukkit.entity.Entity> entities = l.getWorld().getNearbyEntities(l, 30,30,30);
            for (org.bukkit.entity.Entity e : entities) {
                if (e instanceof Player) {
                    double sadRate = 0.15;
                    Random r = new Random();
                    r.nextDouble();
                    if (r.nextDouble() <= sadRate) {
                        Player p = (Player) e;
                        Entity omori_entity = new Entity(p);
                        omori_entity.updateEmotion(new com.juancho.spigot.omorimod.utils.emotions.Sad());
                    };
                }
            }
        }
    
    }

      
}
