package com.juancho.spigot.omorimod;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnAdvancementDoneEvent implements Listener {

    @EventHandler
    public void onAdvancementDone(org.bukkit.event.player.PlayerAdvancementDoneEvent event) {
        double happyRate = 0.15;
        Random r = new Random();
        if (r.nextDouble() < happyRate) {
            com.juancho.spigot.omorimod.utils.Entity omori_entity = new com.juancho.spigot.omorimod.utils.Entity(
                    event.getPlayer());
            omori_entity.updateEmotion(new com.juancho.spigot.omorimod.utils.emotions.Happy());
        }

    }
    
}
