package com.juancho.spigot.omorimod;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.juancho.spigot.omorimod.utils.emotions.Angry;
import com.juancho.spigot.omorimod.utils.emotions.Emotion;
import com.juancho.spigot.omorimod.utils.emotions.Sad;

public class OnFoodLevelChangeEvent implements Listener {

    @EventHandler
    public void onFoodLevelChange(org.bukkit.event.entity.FoodLevelChangeEvent event) {
        
        org.bukkit.entity.Entity entity = event.getEntity();
        if (entity instanceof org.bukkit.entity.Player) {
            // TODO check if food change is due to hunger or due to eating!
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) entity;
            // player.sendMessage("food level changed");
            com.juancho.spigot.omorimod.utils.Entity omori_entity = new com.juancho.spigot.omorimod.utils.Entity(
                    player);
            Emotion currentEmotion = omori_entity.getEmotion();

            if (currentEmotion instanceof Sad) {
                double sadHungerCancelRate = 0.5;
                Random r = new Random();
                double roll = r.nextDouble();
                if (roll < sadHungerCancelRate) {
                    event.setCancelled(true);
                }
            }
            

            int fl = event.getFoodLevel();
            if (fl <= 4) {
                if (currentEmotion instanceof Sad) {
                    // nothing
                } else {
                    omori_entity.updateEmotion(new Angry());
                }
            }
        }

    }
}
